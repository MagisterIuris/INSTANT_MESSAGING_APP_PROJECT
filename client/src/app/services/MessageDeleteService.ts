import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class MessageDeleteService {
  private baseURL = "serverapi";

  constructor(private http: HttpClient) {
  }

  deleteMessage(messageId: string): Observable<any> {
    const url = `${this.baseURL}/message/${messageId}`;
    return this.http.delete(url, { withCredentials: true });
  }
}
