package fr.mightycode.cpoo.server.dto;
import fr.mightycode.cpoo.server.model.Message;
import fr.mightycode.cpoo.server.service.RouterService;

public record MessageDTO(long timestamp, String from, String to, String type, String body) {

  public MessageDTO(RouterService.Message message) {
    this(message.timestamp(), message.from(), message.to(), message.type(), message.body());
  }

  public MessageDTO(Message message) {
    this(message.getTimestamp(), message.getFrom(), message.getTo(), message.getType(), message.getBody());
  }
}

