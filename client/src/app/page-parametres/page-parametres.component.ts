import { Component, OnInit } from "@angular/core";
import { MatDialog } from "@angular/material/dialog";
import { DeleteConfirmationDialogComponent } from "../delete-confirmation-dialog/delete-confirmation-dialog.component";
import { DeleteService } from "../services/DeleteService";
import { LogoutService } from "../services/LogoutService";
import { ThemeService } from "../theme.service";

@Component({
  selector: "app-page-parametres",
  templateUrl: "./page-parametres.component.html",
  styleUrls: ["./page-parametres.component.css"],
})
export class PageParametresComponent implements OnInit {
  themeText = "Light Mode";
  isDarkMode = false;
  userLogin = "";

  constructor(
    public themeService: ThemeService,
    private dialog: MatDialog,
    private deleteService: DeleteService,
    private logoutService: LogoutService,
  ) {
  }

  toggleDarkMode() {
    this.isDarkMode = !this.isDarkMode;
    this.themeText = this.isDarkMode ? "Dark Mode" : "Light Mode";
    if (this.isDarkMode) {
      this.themeService.setDarkTheme();
    } else {
      this.themeService.setLightTheme();
    }
  }

  bees: { src: string; x: number; y: number; rotation: number }[] = [];

  ngOnInit() {
    this.isDarkMode = this.themeService.isDarkTheme();
    this.themeText = this.isDarkMode ? "Dark Mode" : "Light Mode";
    this.generateRandomBees();
    this.deleteService.getCurrentUserLogin().subscribe((login) => {
      this.userLogin = login;
    });
  }

  generateRandomBees() {
    const numberOfBees = 8;
    for (let i = 0; i < numberOfBees; i++) {
      const randomX = Math.random() * window.innerWidth;
      const randomY = Math.random() * window.innerHeight;
      const randomRotation = Math.random() * 360;
      if (i <= 3) {
        this.bees.push({
          src: "../assets/bee1.png",
          x: randomX,
          y: randomY,
          rotation: randomRotation,
        });
      } else {
        this.bees.push({
          src: "../assets/bee.png",
          x: randomX,
          y: randomY,
          rotation: randomRotation,
        });
      }
    }
  }

  signoutButton() {
    this.logoutService.signoutMyUer().subscribe({});
  }

  openDeleteConfirmationDialog(): void {
    const dialogRef = this.dialog.open(DeleteConfirmationDialogComponent, {
      width: "250px",
    });

    dialogRef.afterClosed().subscribe({});
  }
}
