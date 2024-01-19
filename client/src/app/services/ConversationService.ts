import { Injectable } from "@angular/core";
import { Conversation2 } from "../Conversation2";
import { UserProfile } from "../user-profile.model";

@Injectable({
  providedIn: "root",
})
export class ConversationService {
  public conversations: Conversation2[] = [];
  conversationParticipants: { [conversationId: string]: UserProfile[] } = {};

  getConversations() {
    return this.conversations;
  }

  addConversation(conversation: Conversation2) {
    const existingConversation = this.conversations.find(
      (c) => c.from === conversation.from && c.to === conversation.to,
    );

    if (!existingConversation) {
      this.conversations.push(conversation);
      this.conversationParticipants[conversation.id] = [
        { login: conversation.from },
        { login: conversation.to },
      ];
    }
  }
}
