import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class LogoutService {
  private backendUrl = "serverapi/user/signout";

  constructor(private http: HttpClient) {
  }

  signoutMyUer(): Observable<any> {
    return this.http.post(this.backendUrl, { withCredentials: true });
  }
}
