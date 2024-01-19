import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { MessageDTO } from "../MessageDTO";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class UpdateService {
  private backendUrl = "serverapi/message/update_conversation";

  constructor(private http: HttpClient) {
  }

  updateConversation(message: MessageDTO): Observable<any> {
    const url = this.backendUrl;
    return this.http.put(url, message, { withCredentials: true });
  }
}
