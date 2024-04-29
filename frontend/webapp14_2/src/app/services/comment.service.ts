import { Injectable } from '@angular/core';
import {HttpClient,HttpErrorResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {Comment} from "../models/comment.model";

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor() { }
}
