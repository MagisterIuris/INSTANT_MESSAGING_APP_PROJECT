package fr.mightycode.cpoo.server.controller;
import fr.mightycode.cpoo.server.dto.MessageDTO;
import fr.mightycode.cpoo.server.dto.NewMessageDTO;
import fr.mightycode.cpoo.server.dto.UserProfileDTO;
import fr.mightycode.cpoo.server.model.Conversation;
import fr.mightycode.cpoo.server.model.Message;
import fr.mightycode.cpoo.server.model.MyUser;
import fr.mightycode.cpoo.server.repository.ConversationRepository;
import fr.mightycode.cpoo.server.repository.MessageRepository;
import fr.mightycode.cpoo.server.repository.MyUserRepository;
import fr.mightycode.cpoo.server.service.ConversationService;
import fr.mightycode.cpoo.server.service.MessageService;
import fr.mightycode.cpoo.server.service.RouterService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("message")
@RequiredArgsConstructor
public class MessageController {
  @Value("${cpoo.server.domain}")
  private String serverDomain;
  private final RouterService routerService;
  private final ConversationRepository conversationRepository;
  private final MessageRepository messageRepository;
  private final ConversationService conversationService;
  private final MessageService messageService;
  private final MyUserRepository myUserRepository;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public MessageDTO messagePost(final Principal user, @RequestBody final NewMessageDTO postMessage) {
    UUID uuid = UUID.randomUUID();
    long time = System.currentTimeMillis();
    String currentUsername = user.getName();
    String recipientUsername = postMessage.to();
    String[] parts;

    if (recipientUsername.contains("@")) {
      parts = recipientUsername.split("@");
    } else {
      parts = new String[]{recipientUsername, "buzzchat"};
    }

    recipientUsername = parts[0];

    MyUser user_principal = myUserRepository.findByLoginAndDomaine(currentUsername, "@buzzchat");
    MyUser user_dest = myUserRepository.findByLoginAndDomaine(recipientUsername, "@" + parts[1]);

    String userFrom = user_principal.getLogin() + user_principal.getDomaine();
    String userTo = user_dest.getLogin() + user_dest.getDomaine();

    if (userFrom.equals(userTo)) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You cannot send a message to a user with the same adress !");
    }

    Conversation conversation = conversationService.addConversation(userTo);
    if (conversation == null) {
      conversation = conversationRepository.findConversationByUserLogin1AndUserLogin2(userFrom, userTo);
      if (conversation == null) {
        conversation = conversationRepository.findConversationByUserLogin1AndUserLogin2(userTo, userFrom);
      }
    }

    if (conversation == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No conversation between these two users !");
    }

    RouterService.Message routerMessage = new RouterService.Message(
      uuid,
      time,
      userFrom,
      userTo,
      postMessage.type(),
      postMessage.body()
    );

    routerService.routeMessage(routerMessage);
    Message message = new Message(routerMessage);
    message.setConv(conversation);
    conversation.getMessages().add(message);
    conversationService.updateConversation(conversation);
    return new MessageDTO(routerMessage);
  }

  @PostMapping(path = "getAllMessagesOfConversation", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Message>> getAllMessagesOfConversation(@RequestBody UserProfileDTO user) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String principalLogin = authentication.getName();
    String[] parts;

    if (user.login().contains("@")) {
      parts = user.login().split("@");
    } else {
      parts = new String[]{user.login(), "buzzchat"};
    }

    String l = parts[0];
    MyUser u1 = myUserRepository.findByLoginAndDomaine(principalLogin, "@buzzchat");
    MyUser u2 = myUserRepository.findByLoginAndDomaine(l, "@" + parts[1]);
    String userLogin = u2.getLogin() + u2.getDomaine();

    if (userLogin.equals(u1.getLogin() + u1.getDomaine())) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Cannot get the messages because the same user as the authenticated one was provided !");
    }

    if (user == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "At least 1 user is required for a conversation.");
    }

    principalLogin = u1.getLogin() + u1.getDomaine();

    UUID conversationId = conversationService.getConversationByLogins(principalLogin, userLogin);
    if (conversationId == null) {
      conversationId = conversationService.getConversationByLogins(userLogin, principalLogin);
    }

    if (conversationId == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No conversation found for the provided users.");
    }

    Conversation conv = conversationRepository.findConversationByUserLogin1AndUserLogin2(principalLogin, userLogin);
    if (conv == null) {
      conv = conversationRepository.findConversationByUserLogin1AndUserLogin2(userLogin, principalLogin);
    }

    List<Message> messages = conv.getMessages();
    System.out.println(messages);

    if (messages.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No messages found in the conversation.");
    } else {
      return ResponseEntity.ok(messages);
    }
  }

  @PostMapping(path = "getLastMessageOfConversation", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Message> getLastMessageOfConversation(@RequestBody UserProfileDTO user) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String principalLogin = authentication.getName();
    String[] parts;

    if (user.login().contains("@")) {
      parts = user.login().split("@");
    } else {
      parts = new String[]{user.login(), "buzzchat"};
    }

    String l = parts[0];

    MyUser u1 = myUserRepository.findByLoginAndDomaine(principalLogin, "@buzzchat");
    MyUser u2 = myUserRepository.findByLoginAndDomaine(l, "@" + parts[1]);

    String userLogin = u2.getLogin() + u2.getDomaine();
    if (userLogin.equals(u1.getLogin() + u1.getDomaine())) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Cannot get the last message because the same user as the authenticated one was provided !");
    }

    if (user == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You need at least two users for a conversation");
    }

    principalLogin = u1.getLogin() + u1.getDomaine();

    UUID conversationId = conversationService.getConversationByLogins(principalLogin, userLogin);
    if (conversationId == null) {
      conversationId = conversationService.getConversationByLogins(userLogin, principalLogin);
    }

    if (conversationId == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "There is no conversation between these users");
    }

    Conversation conv = conversationRepository.findConversationByUserLogin1AndUserLogin2(principalLogin, userLogin);
    if (conv == null) {
      conv = conversationRepository.findConversationByUserLogin1AndUserLogin2(userLogin, principalLogin);
    }

    Message lastmessage = null;
    List<Message> messages = conv.getMessages();
    if (messages.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No messages found in this conversation");
    } else {
      messages.sort(Comparator.comparing(Message::getTimestamp).reversed());
      lastmessage = messages.get(0);
    }

    if (lastmessage == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No messages found in this conversation");
    } else {
      return ResponseEntity.ok(lastmessage);
    }
  }

  @PostMapping(path = "{messageId}/reactions/{reaction}")
  public ResponseEntity<Message> addReaction(@PathVariable UUID messageId, @PathVariable String reaction) {
    Optional<Message> optionalMessage = messageService.addReaction(messageId, reaction);
    return optionalMessage.map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @DeleteMapping(path = "{messageId}/reactions")
  public ResponseEntity<Message> removeReaction(@PathVariable UUID messageId) {
    Optional<Message> optionalMessage = messageService.removeReaction(messageId);
    return optionalMessage.map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @GetMapping(path = "{messageId}/reactions")
  public ResponseEntity<Map<String, String>> getReaction(@PathVariable UUID messageId) {
    Optional<String> optionalReaction = messageService.getReaction(messageId);
    Map<String, String> response = new HashMap<>();
    response.put("reaction", optionalReaction.orElse(""));
    return ResponseEntity.ok(response);
  }

  @DeleteMapping(path = "{messageId}")
  public ResponseEntity<?> deleteMessage(@PathVariable UUID messageId) {
    try {
      messageService.deleteMessageById(messageId);
      return ResponseEntity.ok().build();
    }
    catch (EntityNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }
}
