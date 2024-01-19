import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { MessageDTO } from "../MessageDTO";
import { UserProfile } from "../user-profile.model";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class MessConvService {
  private baseUrl = "serverapi/message/getAllMessagesOfConversation";

  constructor(private http: HttpClient) {
  }

  getAllMessagesConv(user: UserProfile): Observable<any> {
    const headers = new HttpHeaders({ "Content-Type": "application/json" });
    // Envoyez le message au backend
    return this.http.post<MessageDTO[]>(this.baseUrl, user, {
      headers,
      withCredentials: true,
    });
  }
}
