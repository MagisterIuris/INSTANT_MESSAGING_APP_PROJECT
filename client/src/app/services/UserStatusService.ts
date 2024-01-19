import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class UserStatusService {
  private baseUrl = "serverapi/user";

  constructor(private http: HttpClient) {
  }

  getUserStatus(login: string): Observable<string> {
    const options = {
      responseType: "text" as "json",
      withCredentials: true,
    };
    const url = `${this.baseUrl}/${login}/status`;
    return this.http.get<string>(url, options);
  }
}
