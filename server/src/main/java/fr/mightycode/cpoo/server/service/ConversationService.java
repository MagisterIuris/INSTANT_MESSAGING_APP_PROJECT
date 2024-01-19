package fr.mightycode.cpoo.server.service;

import fr.mightycode.cpoo.server.dto.ConversationDisplayDTO;
import fr.mightycode.cpoo.server.model.Conversation;
import fr.mightycode.cpoo.server.model.Message;
import fr.mightycode.cpoo.server.model.MyUser;
import fr.mightycode.cpoo.server.repository.ConversationRepository;
import fr.mightycode.cpoo.server.repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ConversationService {

  private final ConversationRepository conversationRepository;
  private final MyUserRepository myUserRepository;
  private final MessageService messageService;


  public Conversation addConversation(String login_domaine) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String principalLogin = authentication.getName();
    MyUser u1 = myUserRepository.findByLoginAndDomaine(principalLogin, "@buzzchat");
    // Vérifiez s'il existe déjà une conversation entre ces utilisateurs
    Conversation existingConversation1 = conversationRepository.findConversationByUserLogin1AndUserLogin2(u1.getLogin() + u1.getDomaine(), login_domaine);

    String[] parts;

    if (login_domaine.contains("@")) {
      parts = login_domaine.split("@");
    } else {
      // Si la chaîne ne contient pas "@", définissez-le par défaut sur "buzzchat"
      parts = new String[]{login_domaine, "buzzchat"};
    }

    String login1 = parts[0];
    MyUser u2 = myUserRepository.findByLoginAndDomaine(login1, "@" + parts[1]);


    if (existingConversation1 != null) {
      return null;
    }

    Conversation existingConversation2 = conversationRepository.findConversationByUserLogin1AndUserLogin2(login_domaine, u1.getLogin() + u1.getDomaine());

    if (existingConversation2 != null) {
      return null;
    }

    Conversation conversation = new Conversation();
    conversation.setUserLogin1(principalLogin + u1.getDomaine());
    conversation.setUserLogin2(login1 + u2.getDomaine());
    conversationRepository.save(conversation);
    System.out.println("x");

    return conversation;
  }


  public List<ConversationDisplayDTO> getAllConversationsOfCurrentUser() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    final String loginCurrentUser = (principal instanceof UserDetails) ? ((UserDetails) principal).getUsername() : null;

    MyUser u1 = myUserRepository.findByLoginAndDomaine(loginCurrentUser, "@buzzchat");
    List<Conversation> conversations = new ArrayList<>();

    if (loginCurrentUser != null) {
      List<Conversation> conversationsUser1 = conversationRepository.findConversationsByUserLogin1(u1.getLogin() + u1.getDomaine());
      List<Conversation> conversationsUser2 = conversationRepository.findConversationsByUserLogin2(u1.getLogin() + u1.getDomaine());

      conversations.addAll(conversationsUser1);
      conversations.addAll(conversationsUser2);
    }

    List<ConversationDisplayDTO> conversationDTOs = new ArrayList<>();

    for (Conversation conversation : conversations) {
      String login1 = conversation.getUserLogin1();
      String login2 = conversation.getUserLogin2();

      String[] parts1;
      String[] parts2;

      if (login1.contains("@")) {
        parts1 = login1.split("@");
      } else {
        parts1 = new String[]{login1, "buzzchat"};
      }

      if (login2.contains("@")) {
        parts2 = login2.split("@");
      } else {
        parts2 = new String[]{login2, "buzzchat"};
      }

      login1 = parts1[0];
      login2 = parts2[0];
      MyUser user_dest;

      if (login1.equals(loginCurrentUser)) {
        user_dest = myUserRepository.findByLoginAndDomaine(login2, "@" + parts2[1]);
      } else {
        user_dest = myUserRepository.findByLoginAndDomaine(login1, "@" + parts1[1]);
      }
      System.out.println("chu hna");
      System.out.println(conversation.getId());
      Message lastMessage = conversation.getLastMessage();
      String lastMessageBody = (lastMessage != null) ? lastMessage.getBody() : null;
      long lastMessageTimestamp = (lastMessage != null) ? lastMessage.getTimestamp() : 0;


      if (user_dest == null) {
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User destination does not exist !");
      }


      ConversationDisplayDTO conversationDTO = new ConversationDisplayDTO(conversation.getId(),
        conversation.getUserLogin1(),
        conversation.getUserLogin2(),
        user_dest.getPhoto(),
        lastMessageBody,
        lastMessageTimestamp
      );

      conversationDTOs.add(conversationDTO);
    }

    return conversationDTOs;
  }




 /* public void deleteConversation(Conversation c) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    final String usernameCurrentUser = (principal instanceof UserDetails) ? ((UserDetails) principal).getUsername() : null;

    if (usernameCurrentUser != null && c.getUsers().stream().anyMatch(user -> user.getLogin().equals(usernameCurrentUser))) {
      conversationRepository.delete(c);
    }
  }

  */

  public UUID getConversationByLogins(String login_principal, String login_dest) {
    Conversation conv = conversationRepository.findConversationByUserLogin1AndUserLogin2(login_principal, login_dest);
    if (conv == null) {
      conv = conversationRepository.findConversationByUserLogin1AndUserLogin2(login_dest, login_principal);
    }

    if (conv == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Current user does not have any conversation");
    }
    return conv.getId();
  }

  public void updateConversation(Conversation conversation) {
    conversationRepository.save(conversation);
  }


}





