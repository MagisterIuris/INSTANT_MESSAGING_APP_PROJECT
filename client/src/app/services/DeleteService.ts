import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class DeleteService {
  private backendUrl = "serverapi/user";

  constructor(private http: HttpClient) {
  }

  getCurrentUserLogin() {
    const options = {
      responseType: "text" as "json",
      withCredentials: true,
    };

    return this.http.get<string>(`${this.backendUrl}/username`, options);
  }

  deleteMyUser(login: string): Observable<any> {
    const url = `${this.backendUrl}/${login}`;

    return this.http.delete(url, { withCredentials: true });
  }
}
