package fr.mightycode.cpoo.server.controller;
import fr.mightycode.cpoo.server.dto.ConversationDisplayDTO;
import fr.mightycode.cpoo.server.dto.UserProfileDTO;
import fr.mightycode.cpoo.server.model.Conversation;
import fr.mightycode.cpoo.server.model.MyUser;
import fr.mightycode.cpoo.server.repository.MyUserRepository;
import fr.mightycode.cpoo.server.service.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("conversation")
@RequiredArgsConstructor
public class ConversationController {
  private final ConversationService conversationService;
  private final MyUserRepository myUserRepository;

  @PostMapping(value = "addConversation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ConversationDisplayDTO> addConversation(@RequestBody UserProfileDTO dto) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String principalLogin = authentication.getName();
    String l = dto.login();
    String[] parts;
    if (l.contains("@")) {
      parts = l.split("@");
    } else {
      parts = new String[]{l, "buzzchat"};
    }

    String login = parts[0];
    String domaine = "@" + parts[1];

    if (principalLogin.equals(login)) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Cannot create conversation because login provided equals your login !");
    }

    MyUser u = myUserRepository.findByLoginAndDomaine(login, domaine);

    if (u == null) {
      u = new MyUser();
      u.setNom("");
      u.setLogin(login);
      u.setDomaine("@" + parts[1]);
      u.setPrenom("");
      u.setContacts(new ArrayList<>());
      u.setEmail(l);
      u.setPhoto(null);
      u.setPassword("");
      u.setDateDeNaissance("");
      MyUser u1 = myUserRepository.findByLogin(principalLogin);
      u1.getContacts().add(u);
      u.getContacts().add(u1);
      myUserRepository.save(u);
      myUserRepository.save(u1);
    }

    MyUser user1 = myUserRepository.findByLoginAndDomaine(principalLogin, "@buzzchat");
    MyUser user2 = myUserRepository.findByLoginAndDomaine(login, "@" + parts[1]);

    if (user2 == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found: " + login);
    }

    Conversation conversation = conversationService.addConversation(l);
    ConversationDisplayDTO c;
    if (conversation != null) {
      user1.getContacts().add(user2);
      user2.getContacts().add(user1);
    } else {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Conversation already exists !");
    }

    String lastMessageBody = null;
    if (!conversation.isEmpty() && conversation.getLastMessage() != null) {
      lastMessageBody = conversation.getLastMessage().getBody();
    }

    System.out.println("y");
    myUserRepository.save(user1);
    myUserRepository.save(user2);
    System.out.println("z");
    c = new ConversationDisplayDTO(conversation.getId(), conversation.getUserLogin1(), conversation.getUserLogin2(),
      user2.getPhoto(), lastMessageBody, System.currentTimeMillis());
    return ResponseEntity.ok(c);
  }

  @GetMapping(value = "getAllConversationsOfUser", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<ConversationDisplayDTO>> getAllConversationsOfUser() {
    List<ConversationDisplayDTO> c = conversationService.getAllConversationsOfCurrentUser();
    if (!c.isEmpty()) {
      return ResponseEntity.ok(c);
    } else {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Current user does not have any conversation");
    }
  }
}
