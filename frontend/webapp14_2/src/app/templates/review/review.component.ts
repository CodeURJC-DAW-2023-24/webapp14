import {Component, NgModule} from '@angular/core';
import {CommentService} from "../../services/comment.service";
import {UserService} from "../../services/user.service";
import {Comment} from "../../models/comment.model";
import { Router } from '@angular/router';
import {User} from "../../models/user.model";

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['../../../assets/css/material-dashboard.css',
    '../../../assets/css/nucleo-icons.css',
    '../../../assets/css/nucleo-svg.css',
    '../../../assets/scss/material-dashboard.scss',
    '../../../../src/styles.css'
  ]
})
export class ReviewComponent {
  userComments:Comment[] = [];
  isAdmin:boolean = false;
  isEditor:boolean = false;

  constructor(private commentService:CommentService, private userService: UserService, private router: Router) {}

  ngOnInit(): void {
    this.getCommentsByCurrentUser();
    this.userService.getCurrentUser().subscribe(
      (user:User) => {
        this.isEditor = user.editor;
        this.isAdmin = user.roles.indexOf('ADMIN') > -1;
      }
    )

  }

  getCommentsByCurrentUser(): void {
    this.userService.getCurrentUser().subscribe(currentUser=> {
      const currentUserId = currentUser.id;
      this.commentService.getCommentsByUserId(currentUserId)
        .subscribe((comments: Comment[]) => {
          this.userComments = comments;
        })
    });
  }

  goToIndex(){
    this.router.navigate(['/']);
  }

  goToDashboard(){
    this.router.navigate(['/dashboardPage']);
  }

  goToReview() {
    this.router.navigate(['/reviewPage']);
  }

  goToUserPermissions(){
    this.router.navigate(['/permissionPage']);
  }

  goToProfile(){
    this.router.navigate(['/profilePage']);
  }

  goToEvents(){
    this.router.navigate(['/eventsPage']);
  }
}
