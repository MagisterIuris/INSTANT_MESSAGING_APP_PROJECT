import { Component } from "@angular/core";
import { MatSnackBar } from "@angular/material/snack-bar";
import { Router } from "@angular/router";
import { SignUpService } from "../services/SignUpService";

@Component({
  selector: "app-inscription",
  templateUrl: "./inscription.component.html",
  styleUrls: ["./inscription.component.css"],
})
export class InscriptionComponent {
  errorMessage = "";

  constructor(
    private signUpService: SignUpService,
    private router: Router,
    private snackBar: MatSnackBar,
  ) {
  }

  signUpData = {
    login: "",
    password: "",
    email: "",
    nom: "",
    prenom: "",
    dateDeNaissance: "",
  };

  signUpButton() {
    console.log("je tente de m'inscrire");
    console.log(this.signUpData);

    this.signUpService.signinMyUser(this.signUpData).subscribe({
      next: () => {
        this.snackBar.open("Inscription réussie!", "OK", {
          duration: 3000,
          panelClass: ["custom-snackbar-success"],
        });
        this.router.navigate(["/se-connecter"]);
      },
      error: (error) => {
        if (error.status == 409) {
          this.snackBar.open(
            "L'utilisateur existe dejà. Veuillez réessayer avec un autre nom d'utilisateur.",
            "Réessayer",
            {
              duration: 3000,
              panelClass: ["custom-snackbar"],
            },
          );

          console.error("Erreur lors de l'inscription : ", error);
        }
      },
    });
  }

  navigateToSeConnecter() {
    this.router.navigate(["/se-connecter"]);
  }
}
