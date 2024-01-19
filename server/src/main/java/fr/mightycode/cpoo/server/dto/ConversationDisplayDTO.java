package fr.mightycode.cpoo.server.dto;
import java.util.UUID;

public record ConversationDisplayDTO(UUID id, String from, String to, String picture, String lastMessage,
                                     long timestamp) {
}
