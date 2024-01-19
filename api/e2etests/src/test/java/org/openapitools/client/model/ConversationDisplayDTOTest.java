package org.openapitools.client.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConversationDisplayDTOTest {
  @Test
  public void testConversationDisplayDTO() {
    ConversationDisplayDTO conversation = new ConversationDisplayDTO();
    assertNotNull(conversation);
  }
  @Test
  public void idTest() {
    ConversationDisplayDTO conversation = new ConversationDisplayDTO();
    conversation.setId("12345");
    assertNotNull(conversation.getId());
    assertEquals("12345", conversation.getId());
  }
  @Test
  public void fromTest() {
    ConversationDisplayDTO conversation = new ConversationDisplayDTO();
    conversation.setFrom("user1");
    assertNotNull(conversation.getFrom());
    assertEquals("user1", conversation.getFrom());
  }
  @Test
  public void usernamesTest() {
    ConversationDisplayDTO conversation = new ConversationDisplayDTO();
    conversation.setTo("H");
    conversation.setFrom("A");
    assertNotNull(conversation);
    assertEquals("H", conversation.getTo());
    assertEquals("A", conversation.getFrom());
  }
  @Test
  public void picturesTest() {
    ConversationDisplayDTO conversation = new ConversationDisplayDTO();
    conversation.setPicture("assets/pic.jpg");
    assertNotNull(conversation.getPicture());
    assertEquals("assets/pic.jpg", conversation.getPicture());
  }
  @Test
  public void lastMessageTest() {
    ConversationDisplayDTO conversation = new ConversationDisplayDTO();
    conversation.setLastMessage("Hello, how are you?");
    assertNotNull(conversation.getLastMessage());
    assertEquals("Hello, how are you?", conversation.getLastMessage());
  }
  @Test
  public void timestampTest() {
    ConversationDisplayDTO conversation = new ConversationDisplayDTO();
    conversation.setTimestamp(1635824100L);
    assertNotNull(conversation.getTimestamp());
    assertEquals(1635824100L, conversation.getTimestamp());
  }
}
