import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {LoginService} from "../../services/login.service";
import {CommentsServices} from "../../services/comments.services";
import {Comment} from "../../models/comment.model";

@Component({
  selector: 'app-comments',
  standalone: true,
  imports: [],
  templateUrl: './comments.component.html',
  styleUrl: './comments.component.css'
})
export class CommentsComponent {
  comment:Comment;

  constructor(private router:Router,activatedRoute: ActivatedRoute, public service: CommentsServices, public loginService: LoginService) {
    const id = activatedRoute.snapshot.params['id'];
    service.getComment(id).subscribe(
      comment => this.comment = comment,
      error => console.error(error)
    );
  }

  removeComment() {
    const okResponse = window.confirm('Do you want to remove this comment?');
    if (okResponse) {
      this.service.deleteComment(this.comment).subscribe(
        _ => this.router.navigate(['/comments']),
        error => console.error(error)
      );
    }
  }

  editComment() {
    this.router.navigate(['/comments/edit', this.comment.id]);
  }

  gotoComments() {
    this.router.navigate(['/comments']);
  }
}
