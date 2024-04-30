import { Injectable } from '@angular/core';
import {HttpClient,HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {Comment} from "../models/comment.model";
import {User} from "../models/user.model";
import {catchError} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  constructor(private http: HttpClient) { }

  getComments(): Observable<Comment[]> {
    return this.http.get<Comment[]>(`/api/comments/`);
  }

  getCommentById(id:number): Observable<Comment>{
    return this.http.get<Comment>(`/api/comments/${id}`);
  }
  getCommentsByEventId(eventId:number):Observable<Comment[]>{
    return this.http.get<Comment[]>(`/api/commnets/event/${eventId}`);
  }

  getCommentsByUserId(userId:number):Observable<Comment[]>{
    return this.http.get<Comment[]>(`/api/comments/user/${userId}`);
  }

  addComment(comment: Comment): Observable<Comment> {
    return this.http.post<Comment>(`/api/comments/`,comment);
  }

  addCommentForEvent(eventId: number, comment: Comment): Observable<Comment>{
    return this.http.post<Comment>(`api/comments/event/${eventId}`, comment);
  }

  deleteComment(id: number) {
    return this.http.delete(`/api/comments/${id}`);
  }

  updateComment(id: number, comment:Comment) {
    return this.http.put(`/api/comments/${id}`,comment);
  }

  private handleError(error: HttpErrorResponse) {
    console.error(error);
    return throwError(() => new Error('An error has occuried; please try again later.'));
  }
}
