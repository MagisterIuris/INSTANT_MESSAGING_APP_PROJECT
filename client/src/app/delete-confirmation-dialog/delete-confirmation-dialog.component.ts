import { Component, OnInit } from "@angular/core";
import { MatDialogRef } from "@angular/material/dialog";
import { DeleteService } from "../services/DeleteService";

@Component({
  selector: "app-delete-confirmation-dialog",
  templateUrl: "delete-confirmation-dialog.component.html",
  styleUrls: ["./delete-confirmation-dialog.component.css"],
})
export class DeleteConfirmationDialogComponent implements OnInit {
  loggedInUserLogin!: string;

  constructor(
    public dialogRef: MatDialogRef<DeleteConfirmationDialogComponent>,
    private deleteService: DeleteService,
  ) {
  }

  ngOnInit() {
    this.deleteService.getCurrentUserLogin().subscribe((login) => {
      this.loggedInUserLogin = login;
    });
  }

  deleteButton() {
    if (this.loggedInUserLogin) {
      this.deleteService.deleteMyUser(this.loggedInUserLogin).subscribe(() => {
        console.log("Compte supprimé !");
      });
    }
  }

  confirmDelete(): void {
    this.dialogRef.close("delete");
  }

  cancel(): void {
    this.dialogRef.close();
  }
}
