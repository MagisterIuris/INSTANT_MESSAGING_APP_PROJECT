import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class AuthService {
  private backendUrl = "serverapi/user";

  constructor(private http: HttpClient) {
  }

  isAuthenticated(): Observable<boolean> {
    return this.http.get<boolean>(`${this.backendUrl}/is-authenticated`, {
      withCredentials: true,
    });
  }
}
