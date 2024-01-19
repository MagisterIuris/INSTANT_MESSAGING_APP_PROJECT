import { Component, EventEmitter, Output } from "@angular/core";
import { MatDialog, MatDialogRef } from "@angular/material/dialog";
import { Conversation2 } from "../Conversation2";
import { MessageDTO } from "../MessageDTO";
import { ConvService } from "../services/ConvService";
import { ConversationService } from "../services/ConversationService";
import { MessConvService } from "../services/MessConvService";
import { MessageService } from "../services/MessageService";
import { ThemeService } from "../theme.service";
import { UserProfile } from "../user-profile.model";

@Component({
  selector: "app-my-dialog",
  templateUrl: "./my-dialog.component.html",
  styleUrls: ["./my-dialog.component.css"],
})
export class MyDialogComponent {
  @Output() conversationAdded = new EventEmitter<void>();
  user1!: string;
  messages: MessageDTO[] = [];

  constructor(
    private convService: ConvService,
    public themeService: ThemeService,
    private messageService: MessageService,
    public dialog: MatDialog,
    private conversationService: ConversationService,
    private messConvService: MessConvService,
    public dialogRef: MatDialogRef<MyDialogComponent>,
  ) {
    this.user1 = "";
  }

  addConversationButton() {
    const userData: UserProfile = { login: this.user1 };
    this.convService.addConversation(userData).subscribe((response) => {
      const newConversation = new Conversation2();
      newConversation.id = response.id;
      newConversation.from = response.from;
      newConversation.to = response.to;
      newConversation.picture = response.picture;
      newConversation.lastMessage = response.lastMessage;
      newConversation.timestamp = response.timestamp;
      this.conversationService.addConversation(newConversation);
      this.conversationAdded.emit();
    });
  }

  confirmAdd(): void {
    this.dialogRef.close("delete");
  }
}
