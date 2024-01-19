package fr.mightycode.cpoo.server.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

@Data
@Entity
@Table(name = "conversation")
public class Conversation {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "conversation_id")
  private UUID id;

  @Column(name = "user_login1")
  private String userLogin1;

  @Column(name = "user_login2")
  private String userLogin2;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "conv", cascade = CascadeType.ALL)
  @ToString.Exclude
  private List<Message> messages;

  public Conversation() {
    this.messages = new ArrayList<>();
    this.userLogin1 = "";
    this.userLogin2 = "";
  }

  public boolean isEmpty() {
    return this.messages.isEmpty();
  }

  public Message getLastMessage() {
    if (messages.isEmpty()) {
      return null;
    }
    messages.sort(Comparator.comparing(Message::getTimestamp).reversed());
    return messages.get(0);
  }
}
