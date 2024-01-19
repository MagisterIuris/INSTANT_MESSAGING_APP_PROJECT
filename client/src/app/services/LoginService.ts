import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

interface LoginData {
  login: string;
  password: string;
}

@Injectable({
  providedIn: "root",
})
export class LoginService {
  private backendUrl = "serverapi/user/signin";

  constructor(private http: HttpClient) {
  }

  login(loginData: LoginData): Observable<any> {
    return this.http.post(this.backendUrl, loginData, {
      withCredentials: true,
    });
  }
}
