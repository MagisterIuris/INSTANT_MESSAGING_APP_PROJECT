/*package fr.mightycode.cpoo.server.service;
import fr.mightycode.cpoo.server.model.Conversation;
import fr.mightycode.cpoo.server.model.Message;
import fr.mightycode.cpoo.server.model.MyUser;
import fr.mightycode.cpoo.server.repository.ConversationRepository;
import fr.mightycode.cpoo.server.repository.MessageRepository;
import fr.mightycode.cpoo.server.repository.MyUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class SSEService {

  private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

  private final MyUserRepository myUserRepository;
  private final ConversationRepository conversationRepository;
  private final MessageRepository messageRepository;

  public SSEService(MyUserRepository userRepository, ConversationRepository conversationRepository, MessageRepository messageRepository) {
    this.myUserRepository = userRepository;
    this.conversationRepository = conversationRepository;
    this.messageRepository = messageRepository;
  }

  public void sendEventToSubscribers(Message message) {
    String[] parts1;
    String[] parts2;
    String username1 = message.getFrom();
    String username2 = message.getTo();
    parts1 = username1.split("@");
    parts2 = username2.split("@");
    MyUser u1 = myUserRepository.findByLoginAndDomaine(parts1[0], "@" + parts1[1]);
    if (u1 == null) {
      u1 = new MyUser();
      u1.setNom("");
      u1.setLogin(parts1[0]);
      u1.setDomaine("@" + parts1[1]);
      u1.setPrenom("");
      u1.setContacts(new ArrayList<>());
      u1.setEmail(username1);
      u1.setPhoto(null);
      u1.setPassword("");
      u1.setDateDeNaissance("");
      myUserRepository.save(u1);
    }
    MyUser u2 = myUserRepository.findByLoginAndDomaine(parts2[0], "@" + parts2[1]);
    if (u2 == null) {
      u2 = new MyUser();
      u2.setNom("");
      u2.setLogin(parts2[0]);
      u2.setDomaine("@" + parts2[1]);
      u2.setPrenom("");
      u2.setContacts(new ArrayList<>());
      u2.setEmail(username1);
      u2.setPhoto(null);
      u2.setPassword("");
      u2.setDateDeNaissance("");
      myUserRepository.save(u2);
    }

    Conversation c = conversationRepository.findConversationByUserLogin1AndUserLogin2(message.getFrom(), message.getTo());
    if (c == null) {
      c = conversationRepository.findConversationByUserLogin1AndUserLogin2(message.getTo(), message.getFrom());
    }

    if (c != null) {
      UUID id = message.getId();
      List<Message> allMessages = messageRepository.findAll();
      boolean messageExistsWithSameID = false;
      for (Message m : allMessages) {
        if (m.getId().equals(id)) {
          messageExistsWithSameID = true;
          break;
        }
      }
      if (!messageExistsWithSameID) {
        message.setConv(c);
        messageRepository.save(message);
        c.getMessages().add(message);
        conversationRepository.save(c);
      }

    } else {
      c = new Conversation();
      c.setMessages(new ArrayList());
      c.setUserLogin1(message.getTo());
      c.setUserLogin2(message.getFrom());
      conversationRepository.save(c);
      message.setConv(c);
      messageRepository.save(message);
      c.getMessages().add(message);
      conversationRepository.save(c);
      messageRepository.save(message);
    }

    emitters.forEach(emitter -> {
      try {
        emitter.send(SseEmitter.event()
          .name("event-test")
          .data(message)); // Sending the entire message object
        System.out.println("Message sent");
      }
      catch (Exception e) {
        emitter.complete();
        emitters.remove(emitter);
        System.out.println("Error sending event to client" + e);
      }
    });
  }


  public SseEmitter subscribe() {
    SseEmitter emitter = new SseEmitter();
    emitters.add(emitter);
    emitter.onCompletion(() -> emitters.remove(emitter));
    emitter.onTimeout(() -> emitters.remove(emitter));
    return emitter;
  }
}


 */
package fr.mightycode.cpoo.server.service;

import fr.mightycode.cpoo.server.model.Message;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class SSEService {

  private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

  public void sendEventToSubscribers(Message message) {
    emitters.forEach(emitter -> {
      try {
        emitter.send(SseEmitter.event()
          .name("event-test")
          .data(message)); // Sending the entire message object
        System.out.println("rah dazet lmessage" + message.getBody() + " " + message.getFrom() + " " + message.getTo() + " " + message.getTimestamp() + " " + message.getType());
      }
      catch (Exception e) {
        emitter.complete();
        emitters.remove(emitter);
        System.out.println("Error sending event to client" + e);
      }
    });
  }

  public void sendStatusUpdate(String login, String status) {
    System.out.println("login: " + login + " et status: " + status);
    emitters.forEach(emitter -> {
      try {
        System.out.println("slmmmmmmmmmmmmmmmm");
        emitter.send(SseEmitter.event()
          .name("status-update")
          .data(login + ":" + status)); // Sending the entire message object
        System.out.println("slmmmmmmmmmmmmmmmm");

      }
      catch (Exception e) {
        emitter.complete();
        emitters.remove(emitter);
        System.out.println("Error sending event to client" + e);
      }
    });
  }


  public void sendDeleteEventToSubscribers(UUID messageId) {
    emitters.forEach(emitter -> {
      try {
        emitter.send(SseEmitter.event()
          .name("delete-event")
          .data(messageId.toString())); // Sending the message ID for deletion
        System.out.println("Message deletion event sent for ID: " + messageId);
      }
      catch (Exception e) {
        emitter.complete();
        emitters.remove(emitter);
        System.out.println("Error sending delete event to client: " + e);
      }
    });
  }


  public void sendReactionEventToSubscribers(UUID messageId, String reaction) {
    // Assuming you want to send both message ID and reaction data
    String eventData = messageId.toString() + ":" + reaction;

    emitters.forEach(emitter -> {
      try {
        emitter.send(SseEmitter.event()
          .name("reaction-event")
          .data(eventData)); // Sending both message ID and reaction
        System.out.println("Reaction event sent for message ID: " + messageId);
      }
      catch (Exception e) {
        emitter.complete();
        emitters.remove(emitter);
        System.out.println("Error sending reaction event to client: " + e);
      }
    });
  }


  public void sendDeleteReactionEventToSubscribers(UUID messageId) {
    emitters.forEach(emitter -> {
      try {
        emitter.send(SseEmitter.event()
          .name("delete-reaction-event")
          .data(messageId.toString())); // Sending the message ID for reaction deletion
        System.out.println("Reaction deletion event sent for message ID: " + messageId);
      }
      catch (Exception e) {
        emitter.complete();
        emitters.remove(emitter);
        System.out.println("Error sending delete reaction event to client: " + e);
      }
    });
  }


  public SseEmitter subscribe() {
    final SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
    emitters.add(emitter);

    emitter.onCompletion(() -> {
      emitters.remove(emitter);
      System.out.println("Emitter removed on completion");
    });

    return emitter;
  }
}

