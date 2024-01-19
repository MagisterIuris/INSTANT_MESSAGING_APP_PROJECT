import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

interface SignUpData {
  login: string;
  password: string;
  email: string;
  nom: string;
  prenom: string;
  dateDeNaissance: string;
}

@Injectable({
  providedIn: "root",
})
export class SignUpService {
  private backendUrl = "serverapi/user/signup";

  constructor(private http: HttpClient) {
  }

  signinMyUser(signUpData: SignUpData): Observable<any> {
    return this.http.post(this.backendUrl, signUpData, {
      withCredentials: true,
    });
  }
}
