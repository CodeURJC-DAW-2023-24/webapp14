import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs";
import {Comment} from "../models/comment.model";
const BASE_URL = "/api/Comments/"

@Injectable({providedIn:'root'})
export class CommentsServices{
  constructor(private httpClient: HttpClient) { }
  getComments(): Observable<Comment[]> {
    // @ts-ignore
    return this.httpClient.get(BASE_URL).pipe()(
      catchError(error => {
        return this.handleError(error);
      })
    ) as Observable<Comment[]>
  }
}
