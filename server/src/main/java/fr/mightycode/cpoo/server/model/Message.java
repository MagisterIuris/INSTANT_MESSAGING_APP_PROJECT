package fr.mightycode.cpoo.server.model;
import fr.mightycode.cpoo.server.service.RouterService;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "messages")
public class Message {
  @Id
  private UUID id;
  @Column(name = "timestamp", nullable = false)
  private long timestamp;
  @Column(name = "`from`", nullable = false)
  private String from;
  @Column(name = "`to`", nullable = false)
  private String to;

  @ManyToOne
  @JoinColumn(name = "conversation_id")
  @ToString.Exclude
  @JsonIgnore
  private Conversation conv;

  @Column(name = "type", nullable = false)
  private String type;

  @Column(name = "body", nullable = false)
  private String body;

  @Column(name = "reaction")
  private String reaction;

  public Message() {
    this.to = "";
  }

  public Message(RouterService.Message routerMessage) {
    this.id = routerMessage.id();
    this.timestamp = routerMessage.timestamp();
    this.from = routerMessage.from();
    this.to = routerMessage.to();
    this.type = routerMessage.type();
    this.body = routerMessage.body();
  }
}
