import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class UserNameService {
  private baseUrl = "serverapi/user";

  constructor(private http: HttpClient) {
  }

  getUserName(): Observable<string> {
    const url = `${this.baseUrl}/username`;
    const options = {
      responseType: "text" as "json",
      withCredentials: true,
    };
    return this.http.get<string>(url, options);
  }
}
