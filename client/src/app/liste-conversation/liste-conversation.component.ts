import { Component, ElementRef, OnInit, ViewChild } from "@angular/core";
import { MatDialog } from "@angular/material/dialog";
import { Subscription, catchError, of } from "rxjs";
import { Conversation2 } from "../Conversation2";
import { MessageDTO } from "../MessageDTO";
import { MyDialogComponent } from "../my-dialog/my-dialog.component";
import { ConversationService } from "../services/ConversationService";
import { DeleteService } from "../services/DeleteService";
import { GetConvService } from "../services/GetConvService";
import { MessConvService } from "../services/MessConvService";
import { MessageDeleteService } from "../services/MessageDeleteService";
import { MessageService } from "../services/MessageService";
import { ReactionService } from "../services/ReactionService";
import { UserNameService } from "../services/UserNameService";
import { UserStatusService } from "../services/UserStatusService";
import { ThemeService } from "../theme.service";
import { UserProfile } from "../user-profile.model";
import { UpdateStatService } from "./../services/UpdateStatService";

@Component({
  selector: "app-liste-conversation",
  templateUrl: "./liste-conversation.component.html",
  styleUrls: ["./liste-conversation.component.css"],
})
export class ListeConversationComponent implements OnInit {
  @ViewChild("conversationWindow") conversationWindow!: ElementRef;
  contenuConversation = "";
  afficherChampMessage = false;
  messages: MessageDTO[] = [];
  filterConv = "";
  estEnLigne!: boolean;
  destestEnLigne!: boolean;
  conversationActive2!: Conversation2;
  numberOfConversations!: number;
  userLogin = "";
  userLogin2 = "";
  activeConversationId?: string;
  sseSubscription!: Subscription;
  loginUser = "";
  loginDestinataire = "";

  constructor(
    public themeService: ThemeService,
    private messageService: MessageService,
    public dialog: MatDialog,
    private conversationService: ConversationService,
    public messConvService: MessConvService,
    public deleteService: DeleteService,
    private getConvService: GetConvService,
    private updateStatService: UpdateStatService,
    private userNameService: UserNameService,
    private userStatService: UserStatusService,
    private MessdelServ: MessageDeleteService,
    private reactionService: ReactionService,
  ) {
  }

  conversations2: Conversation2[] = this.conversationService.conversations;
  statusUpdateSubscription!: Subscription;

  getAllMessConvButton() {
    const conversationParticipants =
      this.conversationService.conversationParticipants[
        this.conversationActive2.id
        ];
    let user1: string;

    if (conversationParticipants[1].login !== this.userLogin) {
      user1 = conversationParticipants[1].login;
    } else {
      user1 = conversationParticipants[0].login;
    }

    const user: UserProfile = { login: user1 };
    this.messConvService
      .getAllMessagesConv(user)
      .pipe(
        catchError((error) => {
          console.error("Une erreur s'est produite :", error);
          this.messages = [];
          return of([]);
        }),
      )
      .subscribe((response: MessageDTO[]) => {
        this.messages = response;
        this.messages.forEach((message) => {
          this.getReaction(message.id);
        });
      });
    this.scrollToBottom();
  }

  messageData = {
    to: "",
    type: "texte",
    body: "",
  };

  sendButton() {
    const tempmess = { ...this.messageData };
    this.messageData.body = "";
    this.messageService.envoyerMessage(tempmess).subscribe({
      next: (response: MessageDTO) => {
        const conversation = this.conversationActive2;
        if (this.conversationActive2.to.split("@")[1] !== "buzzchat") {
          this.messages.push(response);
        }
        this.updateLastMessage(conversation, response);
        this.scrollToBottom();
      },
      error: (error) => {
        console.error("Erreur lors de l'envoi du message :", error);
      },
    });

    this.trierConversations();
  }

  isBuzzchatDomain(to: string): boolean {
    const splitTo = to.split("@");
    return splitTo.length === 2 && splitTo[1] === "buzzchat";
  }

  openDialog() {
    const dialogRef = this.dialog.open(MyDialogComponent, {
      width: "400px",
    });
    dialogRef.afterClosed().subscribe({});
  }

  ngOnInit() {
    this.themeService.loadTheme();
    this.deleteService.getCurrentUserLogin().subscribe((login: string) => {
      this.userLogin2 = login;
      this.userLogin = `${login}@buzzchat`;
    });

    this.initializeConversations();

    const eventSource = new EventSource("serverapi/sse/stream");

    eventSource.addEventListener("open", () => {
      console.log("SSE Connection is established");
    });

    eventSource.addEventListener("event-test", (event) => {
      const eventData = JSON.parse(event.data);
      this.refreshConversations();

      const newMessage: MessageDTO = {
        id: eventData.id,
        from: eventData.from,
        to: eventData.to,
        timestamp: eventData.timestamp,
        type: eventData.type,
        body: eventData.body,
        reaction: eventData.reaction,
      };

      this.arrangeMessagesInConversations(newMessage);
      this.refreshConversations();
    });

    this.getUserName();

    eventSource.addEventListener("status-update", (event) => {
      const [login, status] = event.data.split(":");

      if (login === this.loginDestinataire) {
        if (status === "offline") {
          this.destestEnLigne = false;
        }
        if (status === "online") {
          this.destestEnLigne = true;
        }
      }
    });

    eventSource.addEventListener("reaction-event", (event) => {
      const [messageId, reaction] = event.data.split(":");
      const messageToUpdate = this.messages.find(
        (message) => message.id === messageId,
      );

      if (messageToUpdate) {
        messageToUpdate.reaction = reaction;
        messageToUpdate.selectedEmoji = reaction;
      }
    });

    eventSource.addEventListener("delete-reaction-event", (event) => {
      const messageId = event.data;
      const messageToUpdate = this.messages.find(
        (message) => message.id === messageId,
      );

      if (messageToUpdate) {
        messageToUpdate.reaction = "";
        messageToUpdate.selectedEmoji = "";
      }
    });

    eventSource.addEventListener("delete-event", (event) => {
      const messageId = event.data;
      this.deleteMessage(messageId);
      this.deleteMessageLocally(messageId);
      this.refreshConversations();
    });
  }

  trierConversations() {
    this.conversations2.sort((a, b) => b.timestamp - a.timestamp);
  }

  deleteMessageLocally(messageId: string) {
    const index = this.messages.findIndex(
      (message) => message.id === messageId,
    );
    if (index !== -1) {
      this.messages.splice(index, 1);
      if (this.messages.length === 0) {
        const emptyMessage: MessageDTO = {
          id: "",
          from: "",
          to: "",
          timestamp: 0,
          type: "",
          body: "",
          reaction: "",
        };
        this.updateLastMessage(this.conversationActive2, emptyMessage);
      } else {
        const lastMessage = this.messages.reduce((prev, current) => {
          return prev.timestamp > current.timestamp ? prev : current;
        }, this.messages[0]);
        this.updateLastMessage(this.conversationActive2, lastMessage);
      }
    }
  }

  toggleAction(message: MessageDTO) {
    if (message.showEmojis || message.showDelete) {
      message.showEmojis = false;
      message.showDelete = false;
    } else {
      message.showEmojis = message.from !== this.userLogin;
      message.showDelete = message.from === this.userLogin;
    }
  }

  showDelete(message: MessageDTO) {
    message.showDelete = !message.showDelete;
    message.showEmojis = false;
  }

  showEmojis(message: MessageDTO) {
    message.showEmojis = !message.showEmojis;
    message.showDelete = false;
  }

  cancelEmoji(messageId: string) {
    const message = this.messages.find((msg) => msg.id === messageId);
    if (message) {
      message.selectedEmoji = "";
      this.removeReaction(messageId);
    }
  }

  deleteMessage(messageId: string) {
    this.MessdelServ.deleteMessage(messageId).subscribe({
      next: () => {
        console.log("Message supprimé avec succès");
      },
      error: (error) => {
        console.error("Erreur lors de la suppression du message :", error);
      },
    });
  }

  addReaction(messageId: string, reaction: string) {
    this.reactionService.addReaction(messageId, reaction).subscribe({
      next: () => {
        console.log("Réaction ajoutée avec succès");
      },
      error: (error) => {
        console.error("Erreur lors de l'ajout de la réaction :", error);
      },
    });
    const message = this.messages.find((msg) => msg.id === messageId);
    if (message) {
      message.selectedEmoji = reaction;
      message.showEmojis = false;
    }
  }

  removeReaction(messageId: string) {
    this.reactionService.removeReaction(messageId).subscribe({
      next: () => {
        console.log("Réaction supprimée avec succès");
      },
      error: (error) => {
        console.error("Erreur lors de la suppression de la réaction :", error);
      },
    });
  }

  getReaction(messageId: string) {
    this.reactionService.getReaction(messageId).subscribe({
      next: (reactionData) => {
        const message = this.messages.find((msg) => msg.id === messageId);
        if (message) {
          message.selectedEmoji = reactionData.reaction;
        }
      },
      error: (error) => {
        console.error("Erreur lors de la récupération de la réaction :", error);
      },
    });
  }

  getUserStatus(): void {
    this.userStatService.getUserStatus(this.loginUser).subscribe(
      (status: string) => {
        if (status === "online") {
          this.estEnLigne = true;
        } else {
          this.estEnLigne = false;
        }
      },
      (error) => {
        console.error("Erreur lors de la récupération du statut : ", error);
      },
    );
  }

  getDestinataireStatus(destinataire: string): void {
    this.userStatService.getUserStatus(destinataire).subscribe(
      (status: string) => {
        if (status === "online") {
          this.destestEnLigne = true;
        } else {
          this.destestEnLigne = false;
        }
      },
      (error) => {
        console.error("Erreur lors de la récupération du statut : ", error);
      },
    );
  }

  private initializeConversations() {
    this.getConvService.getAllConversationsOfUser().subscribe({
      next: (conversations: Conversation2[]) => {
        conversations.forEach((conversation) => {
          this.conversationService.addConversation(conversation);
        });
        this.trierConversations();
        if (this.activeConversationId !== undefined) {
          this.conversationActive2 = conversations.find(
            (conv) => conv.id === this.activeConversationId,
          ) as Conversation2;
        }
      },
      error: (error) => {
        console.error(
          "Erreur lors de la récupération des conversations :",
          error,
        );
      },
    });
  }

  getUserName() {
    this.userNameService.getUserName().subscribe(
      (username: string) => {
        this.loginUser = username;
        this.getUserStatus();
      },
      (error) => {
        console.error(
          "Erreur lors de la récupération du nom d'utilisateur : ",
          error,
        );
      },
    );
  }

  refreshConversations() {
    this.initializeConversations();
  }

  onItemClick2(event: Event, item: Conversation2) {
    event.stopPropagation();
    const displayName = item.to !== this.userLogin ? item.to : item.from;
    this.afficherChampMessage = true;
    item.unread = false;
    this.conversationActive2 = item;
    this.messageData.to = displayName;

    const splitDisplayName = displayName.split("@");

    if (splitDisplayName.length === 2 && splitDisplayName[1] === "buzzchat") {
      this.loginDestinataire = splitDisplayName[0];
      this.getDestinataireStatus(this.loginDestinataire);
    }
  }

  changerEtatConnexion() {
    this.estEnLigne = !this.estEnLigne;
    const newStatus = this.estEnLigne ? "online" : "offline";
    this.updateStatService.updateStatus(this.loginUser, newStatus).subscribe(
      () => {
        console.log("Statut de l'utilisateur mis à jour avec succès");
      },
      (error) => {
        console.error("Erreur lors de la mise à jour du statut : ", error);
        this.estEnLigne = !this.estEnLigne;
      },
    );
  }

  scrollToBottom() {
    if (this.conversationWindow) {
      this.conversationWindow.nativeElement.scrollTop =
        this.conversationWindow.nativeElement.scrollHeight;
    }
  }

  arrangeMessagesInConversations(message: MessageDTO) {
    this.refreshConversations();
    const existingConversation = this.conversations2.find(
      (conv) =>
        (conv.from === message.from && conv.to === message.to) ||
        (conv.from === message.to && conv.to === message.from),
    );
    if (existingConversation) {
      const newMessage = new MessageDTO();
      newMessage.id = message.id;
      newMessage.from = message.from;
      newMessage.to = message.to;
      newMessage.timestamp = message.timestamp;
      newMessage.type = message.type;
      newMessage.body = message.body;

      this.getReaction(newMessage.id);

      this.updateLastMessage(existingConversation, newMessage);

      if (existingConversation !== this.conversationActive2) {
        existingConversation.unread = true;
      } else {
        const activeConversation = this.conversations2.find(
          (conv) =>
            (conv.from === this.conversationActive2.from &&
              conv.to === this.conversationActive2.to) ||
            (conv.from === this.conversationActive2.to &&
              conv.to === this.conversationActive2.from),
        );

        if (
          activeConversation &&
          activeConversation.id === existingConversation.id
        ) {
          this.messages.push(newMessage);
          this.scrollToBottom();
        }
      }
    } else {
      setTimeout(() => {
        this.arrangeMessagesInConversations(message);
      }, 200);
    }
  }

  updateLastMessage(conversation: Conversation2, newMessage: MessageDTO) {
    conversation.lastMessage = newMessage.body;
    conversation.timestamp = newMessage.timestamp;
  }
}
