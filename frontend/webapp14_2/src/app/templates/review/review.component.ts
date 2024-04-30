import { Component } from '@angular/core';
import {CommentService} from "../../services/comment.service";
import {UserService} from "../../services/user.service";
import {Comment} from "../../models/comment.model";
import {CommonModule} from "@angular/common";
import {NgModel} from "@angular/forms";


@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrl: './review.component.css'
})
export class ReviewComponent {
  userComments:Comment[] = [];

  constructor(private commentService:CommentService, private userService: UserService) {}

  ngOnInit(): void {
    this.getCommentsByCurrentUser();
  }

  getCommentsByCurrentUser(): void {
    this.userService.getCurrentUser().subscribe(currentUser => {
      const currentUserId = currentUser.id;
      this.commentService.getCommentsByUserId(currentUserId)
        .subscribe((comments: Comment[]) => {
          this.userComments = comments;
        })
    });
  }
}
