import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {User} from "../models/user.model";
import {HttpClient} from "@angular/common/http";


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getCurrentUser(): Observable<User> {
    return this.http.get<User>('/api/me');
  }
}
