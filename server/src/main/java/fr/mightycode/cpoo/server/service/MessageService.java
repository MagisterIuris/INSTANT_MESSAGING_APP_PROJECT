package fr.mightycode.cpoo.server.service;

import fr.mightycode.cpoo.server.model.Conversation;
import fr.mightycode.cpoo.server.model.Message;
import fr.mightycode.cpoo.server.repository.ConversationRepository;
import fr.mightycode.cpoo.server.repository.MessageRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {

  @Value("${cpoo.server.domain}")
  private String serverDomain;

  private final MessageRepository messageRepository;

  private final ConversationRepository convRepo;

  private final SSEService sseService;

  // All incoming messages from the router are queued in per recipient queues,
  // and all messages posted by clients are queued in per sender queues
  private final Map<String, BlockingQueue<Message>> messageQueues = new HashMap<>();

  /**
   * Get the message queue of the recipient (create it if it does not exist).
   *
   * @param to The recipient address
   * @return the message queue of the recipient
   */
  private BlockingQueue<Message> getQueue(String to) {
    BlockingQueue<Message> messageQueue = messageQueues.get(to);
    System.out.println("messageQueue");
    System.out.println(messageQueue);
    if (messageQueue == null) {
      messageQueue = new LinkedBlockingQueue<>();
      messageQueues.put(to, messageQueue);
    }
    return messageQueue;
  }

  /**
   * Store a message in the database.
   *
   * @param message The message to store
   * @return the stored message
   */
  public Message storeMessage(Message message) {
    log.info("Storing message {}", message);
    return messageRepository.save(message);
  }

  /**
   * Queue a message in the message queue of the recipient.
   *
   * @param message The message to queue
   */
  public void queueMessageForRecipient(Message message) {
    log.info("Queueing message for recipient {}", message);
    System.out.println(message);
    getQueue(message.getTo()).add(message);
  }


  /**
   * Queue a message in the message queue of the sender.
   *
   * @param message The message to queue
   */
  public void queueMessageForSender(Message message) {
    log.info("Queueing message for sender {}", message);
    getQueue(message.getFrom()).add(message);
  }

  /**
   * Get all messages send to or by a given user.
   *
   * @param login The user login
   * @return the list of messages sent to or by the user
   */
  public List<Message> getMessages(String login) {
    String userAddress = login + "@" + serverDomain;
    return messageRepository.findByFromOrToIgnoreCaseOrderByTimestampDesc(userAddress, userAddress);
  }

  /**
   * Get the next message sent to a given user.
   * This call blocks until an incoming message is received for the user or until a timeout expires.
   *
   * @param to The user address
   * @return the message
   */
  public Message getNextMessage(String to) throws InterruptedException {
    System.out.println(getQueue(to));
    return getQueue(to).poll(5, TimeUnit.SECONDS);
  }

  public void deleteMessageById(UUID messageId) {
    // Récupérer le message par son ID s'il existe
    Optional<Message> optionalMessage = messageRepository.findById(messageId);

    // Vérifier si le message existe avant de le supprimer
    if (optionalMessage.isPresent()) {
      Message messageToDelete = optionalMessage.get();
      Conversation c = convRepo.findConversationByUserLogin1AndUserLogin2(messageToDelete.getFrom(), messageToDelete.getTo());
      if (c == null) {
        c = convRepo.findConversationByUserLogin1AndUserLogin2(messageToDelete.getTo(), messageToDelete.getFrom());
      }

      if (c == null) {
        throw new EntityNotFoundException("Conversation not found with ID: " + c.getId());
      }

      c.getMessages().remove((messageToDelete));
      convRepo.save(c);
      messageRepository.deleteById(messageToDelete.getId());
      sseService.sendDeleteEventToSubscribers(messageToDelete.getId());
    }
  }


  public Optional<Message> addReaction(UUID messageId, String reaction) {
    Optional<Message> optionalMessage = messageRepository.findById(messageId);
    if (optionalMessage.isPresent()) {
      Message message = optionalMessage.get();
      message.setReaction(reaction);
      sseService.sendReactionEventToSubscribers(message.getId(), reaction);
      return Optional.of(messageRepository.save(message));
    }
    return Optional.empty();
  }

  public Optional<Message> removeReaction(UUID messageId) {
    Optional<Message> optionalMessage = messageRepository.findById(messageId);
    if (optionalMessage.isPresent()) {
      Message message = optionalMessage.get();
      message.setReaction(null);
      sseService.sendDeleteReactionEventToSubscribers(message.getId());
      return Optional.of(messageRepository.save(message));
    }
    return Optional.empty();
  }

  public Optional<String> getReaction(UUID messageId) {
    return messageRepository.findById(messageId).map(Message::getReaction);
  }
}
