import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

interface NewMessageDto {
  to: string;
  type: string;
  body: string;
}

@Injectable({
  providedIn: "root",
})
export class MessageService {
  private baseUrl = "serverapi/message";

  constructor(private http: HttpClient) {
  }

  envoyerMessage(messageData: NewMessageDto): Observable<any> {
    return this.http.post(`${this.baseUrl}`, messageData, {
      withCredentials: true,
    });
  }
}
