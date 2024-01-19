import { Component, OnInit } from "@angular/core";
import { MatSnackBar } from "@angular/material/snack-bar";
import { Router } from "@angular/router";
import { AuthService } from "../services/AuthService";
import { LoginService } from "../services/LoginService";

@Component({
  selector: "app-se-connecter",
  templateUrl: "./se-connecter.component.html",
  styleUrls: ["./se-connecter.component.css"],
})
export class SeConnecterComponent implements OnInit {
  title = "Log in to Buzz";
  usernameOrEmail = "";
  password = "";

  bees: { src: string; x: number; y: number; rotation: number }[] = [];

  ngOnInit() {
    this.generateRandomBees();
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

  loginData = {
    login: "",
    password: "",
  };

  constructor(
    private loginService: LoginService,
    private router: Router,
    private authService: AuthService,
    private snackBar: MatSnackBar,
  ) {
  }

  loginButton() {
    this.loginService.login(this.loginData).subscribe(
      () => {
        this.authService.isAuthenticated().subscribe((isAuthenticated) => {
          if (isAuthenticated) {
            this.router.navigate(["/liste-conversation"]);
          }
        });
      },
      (error) => {
        // Gérer les erreurs ici
        if (error.status === 401) {
          this.snackBar.open(
            "L'utilisateur n'existe pas. Le mot de passe ou le nom d'utilisateur sont incorrects !",
            "Réessayer",
            {
              duration: 3000,
              panelClass: ["custom-snackbar"],
            },
          );
        } else if (error.status === 409) {
          this.snackBar.open("L'utilisateur est déjà connecté.", "Réessayer", {
            duration: 3000,
            panelClass: ["custom-snackbar"],
          });
        } else {
          console.error("Erreur inattendue", error);
        }
      },
    );
  }
}
