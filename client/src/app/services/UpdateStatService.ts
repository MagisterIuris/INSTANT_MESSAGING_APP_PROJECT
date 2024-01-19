import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class UpdateStatService {
  private baseUrl = "serverapi/user";

  constructor(private http: HttpClient) {
  }

  updateStatus(login: string, status: string): Observable<any> {
    const url = `${this.baseUrl}/${login}/status/${status}`;
    return this.http.put(url, { withCredentials: true });
  }
}
