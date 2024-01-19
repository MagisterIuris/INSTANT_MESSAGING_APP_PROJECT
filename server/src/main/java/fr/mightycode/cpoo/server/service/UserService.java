package fr.mightycode.cpoo.server.service;

import fr.mightycode.cpoo.server.dto.UserCredentialsDTO;
import fr.mightycode.cpoo.server.model.Message;
import fr.mightycode.cpoo.server.model.MyUser;
import fr.mightycode.cpoo.server.model.Conversation;
import fr.mightycode.cpoo.server.repository.ConversationRepository;
import fr.mightycode.cpoo.server.repository.MessageRepository;
import fr.mightycode.cpoo.server.repository.MyUserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

  @Autowired
  private final PasswordEncoder passwordEncoder;
  private final UserDetailsManager userDetailsManager;
  private final MyUserRepository UserRepository;
  private final HttpServletRequest httpServletRequest;
  private final ConversationRepository conversationRepository;
  private final MyUserRepository myUserRepository;

  private final MessageRepository messageRepository;

  private final SSEService sseService;

  public boolean signup(String login, String password, String email, String nom, String prenom, String dateDeNaissance, String photo) {
    MyUser user = new MyUser();
    user.setLogin(login);
    user.setPassword(passwordEncoder.encode(password));
    user.setEmail(email);
    user.setNom(nom);
    user.setPrenom(prenom);
    user.setDateDeNaissance(dateDeNaissance);
    user.setPhoto(photo);
    user.setDomaine("@buzzchat");
    UserRepository.save(user);
    return true;
  }


  public int signin(final String login, final String password) throws ServletException {
    MyUser u = UserRepository.findByLogin(login);

    if (u == null) {
      // L'utilisateur n'existe pas dans la base de données
      return 1;
    }

    if (passwordEncoder.matches(password, u.getPassword())) {
      final HttpSession session = httpServletRequest.getSession(false);
      if (session != null)
        return 2; // L'user déjà connecté

      userDetailsManager.createUser(new User(login, u.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER"))));
      httpServletRequest.login(login, password);
      httpServletRequest.getSession(true);
      return 0; // Connexion réussie
    }

    return 3; // Mauvais mot de passe
  }


  public void signout() throws ServletException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null && authentication.isAuthenticated()) {
      Object principal = authentication.getPrincipal();
      System.out.println(principal);
      if (principal instanceof User) {
        User u = (User) principal;
        userDetailsManager.deleteUser(u.getUsername());
        httpServletRequest.logout();
      } else if (principal instanceof String) {
        MyUser u = myUserRepository.findByLogin((String) principal);
        System.out.println(principal);
        userDetailsManager.deleteUser(this.getLogin());
        httpServletRequest.logout();
      }
    } else {
      // L'utilisateur n'est pas authentifié
      throw new ServletException("L'utilisateur n'est pas authentifié");
    }
  }

  public void updateStatus(String login, String status) {
    MyUser user = UserRepository.findByLogin(login);
    if (user != null) {
      user.setStatus(status);
      UserRepository.save(user);
      System.out.println("helllooooooeoeoeoeo");
      sseService.sendStatusUpdate(login, status);
    }
  }

  public boolean delete(String login) throws ServletException {
    if (!userDetailsManager.userExists(login))
      return false;

    MyUser user = UserRepository.findByLogin(login);
    List<Conversation> conv = conversationRepository.findConversationsByUserLogin1(login + user.getDomaine());
    for (Conversation conversation : conv) {
      List<Message> messages = conversation.getMessages();

      // Supprimez les messages de la conversation
      for (Message m : messages) {
        messageRepository.deleteById(m.getId());
      }
    }

    // Récupérez les contacts de l'utilisateur
    List<MyUser> contacts = user.getContacts();

    // Supprimez l'utilisateur de la liste de contacts de ses contacts
    for (MyUser contact : contacts) {
      contact.getContacts().remove(user);
    }
    userDetailsManager.deleteUser(login);
    for (Conversation conversation : conv) {
      conversationRepository.deleteById(conversation.getId());
    }
    UserRepository.deleteById(user.getId());
    this.signout();
    return true;
  }


  public String getLogin() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) {
      MyUser u = UserRepository.findByLogin(((UserDetails) principal).getUsername());
      return u.getLogin();
    } else {
      return "Principal is not an instance of UserDetails";
    }
  }

  public String getUserStatus(String login) {
    MyUser user = UserRepository.findByLogin(login);
    if (user != null) {
      return user.getStatus();
    } else {
      return "User not found";
    }
  }


}
