package org.openapitools.client.model;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MessageDTOTest {
  @Test
  public void testMessageDTO() {
    MessageDTO messageDTO = new MessageDTO();
    messageDTO.setTimestamp(123456789L);
    messageDTO.setFrom("JohnDoe");
    Assertions.assertEquals(123456789L, messageDTO.getTimestamp());
    Assertions.assertEquals("JohnDoe", messageDTO.getFrom());
  }
  @Test
  public void timestampTest() {
    MessageDTO messageDTO = new MessageDTO();
    messageDTO.setTimestamp(123456789L);
    Assertions.assertEquals(123456789L, messageDTO.getTimestamp());
  }
  @Test
  public void fromTest() {
    MessageDTO messageDTO = new MessageDTO();
    messageDTO.setFrom("JohnDoe");
    Assertions.assertEquals("JohnDoe", messageDTO.getFrom());
  }
  @Test
  public void toTest() {
    MessageDTO messageDTO = new MessageDTO();
    String toList = "Alice";
    messageDTO.setTo(toList);
    Assertions.assertEquals(toList, messageDTO.getTo());
  }
  @Test
  public void typeTest() {
    MessageDTO messageDTO = new MessageDTO();
    messageDTO.setType("text");
    Assertions.assertEquals("text", messageDTO.getType());
  }
  @Test
  public void bodyTest() {
    MessageDTO messageDTO = new MessageDTO();
    messageDTO.setBody("Hello, World!");
    Assertions.assertEquals("Hello, World!", messageDTO.getBody());
  }
}
