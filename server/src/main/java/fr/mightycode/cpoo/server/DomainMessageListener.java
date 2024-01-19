package fr.mightycode.cpoo.server;
import fr.mightycode.cpoo.server.model.Conversation;
import fr.mightycode.cpoo.server.model.Message;
import fr.mightycode.cpoo.server.model.MyUser;
import fr.mightycode.cpoo.server.repository.ConversationRepository;
import fr.mightycode.cpoo.server.repository.MessageRepository;
import fr.mightycode.cpoo.server.repository.MyUserRepository;
import fr.mightycode.cpoo.server.service.MessageService;
import fr.mightycode.cpoo.server.service.RouterService;
import fr.mightycode.cpoo.server.service.SSEService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class DomainMessageListener implements RouterService.MessageListener {
  @Value("${cpoo.server.domain}")
  private String serverDomain;
  @Value("${cpoo.router.url}")
  private String routerUrl;
  private final MessageService messageService;
  private final MessageRepository messageRepository;
  private final ConversationRepository convrepo;
  private final MyUserRepository userrepo;
  private final SSEService sseService;

  @Override
  public String getServerDomain() {
    return serverDomain;
  }

  @Override
  public String getRouterUrl() {
    return routerUrl;
  }

  @Override
  public void onMessageReceived(RouterService.Message routerMessage) {
    log.info("Storing message received from router: {}", routerMessage);
    Message message = new Message(routerMessage);
    log.info("Queueing message{}", message);
    messageService.queueMessageForRecipient(message);
    sseService.sendEventToSubscribers(message);
    String[] parts1;
    String[] parts2;
    String username1 = message.getFrom();
    String username2 = message.getTo();
    parts1 = username1.split("@");
    parts2 = username2.split("@");
    MyUser u1 = userrepo.findByLoginAndDomaine(parts1[0], "@" + parts1[1]);
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
      userrepo.save(u1);
    }

    MyUser u2 = userrepo.findByLoginAndDomaine(parts2[0], "@" + parts2[1]);
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
      userrepo.save(u2);
    }

    Conversation c = convrepo.findConversationByUserLogin1AndUserLogin2(message.getFrom(), message.getTo());
    if (c == null) {
      c = convrepo.findConversationByUserLogin1AndUserLogin2(message.getTo(), message.getFrom());
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
        convrepo.save(c);
      }

    } else {
      c = new Conversation();
      c.setMessages(new ArrayList());
      c.setUserLogin1(message.getTo());
      c.setUserLogin2(message.getFrom());
      convrepo.save(c);
      message.setConv(c);
      messageRepository.save(message);
      c.getMessages().add(message);
      convrepo.save(c);
      messageRepository.save(message);
    }
  }
}

