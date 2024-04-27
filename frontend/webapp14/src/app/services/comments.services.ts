import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs";
import {Comment} from "../models/comment.model";
import {error} from "@angular/compiler-cli/src/transformers/util";
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

  getComment(id: number): Observable<Comment> {
    return this.httpClient.get(BASE_URL + id).pipe(
      catchError(error => {
        return this.handleError(error);
      })
    ) as Observable<Comment>;
  }

  addComment(comment: Comment) {

    if (!comment.id) {
      return this.httpClient.post(BASE_URL, comment)
        .pipe(
          catchError(error => this.handleError(error))
        );
    } else {
      return this.httpClient.put(BASE_URL + comment.id, comment).pipe(
        catchError(error => this.handleError(error))
      );
    }
  }

  deleteComment(comment: Comment) {
    return this.httpClient.delete(BASE_URL + comment.id).pipe(
      catchError(error => this.handleError(error))
    );
  }

  updateComment(comment: Comment) {
    return this.httpClient.put(BASE_URL + comment.id, comment).pipe(
      catchError(error => this.handleError(error))
    );
  }

  private handleError(error: any) {
    console.log("ERROR:");
    console.error(error);
    return throwError("Server error (" + error.status + "): " + error.text())
  }
}
