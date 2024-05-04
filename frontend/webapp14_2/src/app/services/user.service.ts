import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user.model';
import { UserDto } from '../models/userDto.model';
import { Event} from "../models/event.model";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getCurrentUser(): Observable<User> {
    return this.http.get<User>(`/api/users/me`);
  }

  doLogin(credentials:{ username: string, password: string } ): Observable<User> {
    return this.http.post<User>(`api/auth/login`, credentials);
  }

  doRegister(dataUser: { id: number, name: string, surname: string, email: string, encodedPassword: string, roles: string[], studyCenter: string, phone: number, editor: boolean, events: Event[], NICK: string }): Observable<User> {
    return this.http.post<User>(`/api/users/`, dataUser);
  }

  getUserEvents(userId: number): Observable<Event[]> {
    return this.http.get<Event[]>(`/api/users/${userId}/events`);
  }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(`/api/users/`);
  }

  putPermission(id: number): Observable<User>{
    return this.http.post<User>(`/api/users/grantPermissions/${id}`, {});
  }

  quitPermission(id: number): Observable<User>{
    return this.http.post<User>(`/api/users/quitPermission/${id}`, {});
  }

}
