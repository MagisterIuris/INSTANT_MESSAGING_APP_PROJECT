package fr.mightycode.cpoo.server.repository;
import fr.mightycode.cpoo.server.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, UUID> {
  List<Conversation> findConversationsByUserLogin1(String login);
  Conversation findConversationByUserLogin1AndUserLogin2(String login1, String login2);
  List<Conversation> findConversationsByUserLogin2(String login);
  void deleteById(UUID id);


}
