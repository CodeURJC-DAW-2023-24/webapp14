import { Component } from '@angular/core';
import {CommentService} from "../../services/comment.service";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrl: './review.component.css'
})
export class ReviewComponent {
  userComments:Comment[] = [];

  constructor(private commentService:CommentService, private userService: UserService) {}

  ngOnInit(): void {
    this.getCommentsByCurrentsUser();
  }
  getCommentsByCurrentsUser():void{
    const currentUser = this.userService.getCurrentUser();
    const currentUserId = currentUser.nick;
    this.commentService.getCommentsByUserId(currentUserId)
      .subscribe(comments => this.userComments = comments);
  }
}
