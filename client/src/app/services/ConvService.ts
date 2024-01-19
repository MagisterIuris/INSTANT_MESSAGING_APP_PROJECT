import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { UserProfile } from "../user-profile.model";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class ConvService {
  private backendUrl = "serverapi/conversation/addConversation";

  constructor(private http: HttpClient) {
  }

  addConversation(user: UserProfile): Observable<any> {
    const url = this.backendUrl;
    return this.http.post(url, user, { withCredentials: true });
  }
}
