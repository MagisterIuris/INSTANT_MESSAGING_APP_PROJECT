import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class ReactionService {
  private baseURL = "serverapi";

  constructor(private http: HttpClient) {
  }

  addReaction(messageId: string, reaction: string): Observable<any> {
    const url = `${this.baseURL}/message/${messageId}/reactions/${reaction}`;
    return this.http.post(url, { withCredentials: true });
  }

  removeReaction(messageId: string): Observable<any> {
    const url = `${this.baseURL}/message/${messageId}/reactions`;
    return this.http.delete(url, { withCredentials: true });
  }

  getReaction(messageId: string): Observable<any> {
    const url = `${this.baseURL}/message/${messageId}/reactions`;
    return this.http.get(url, { withCredentials: true });
  }
}
