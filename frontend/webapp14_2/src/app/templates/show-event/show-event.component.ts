import { Component } from '@angular/core';
import {ActivatedRoute,Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {CommentService} from "../../services/comment.service";
import {EventService} from "../../services/event.service";
import {Comment} from "../../models/comment.model";
import { Event } from "../../models/event.model";
import {UserService} from "../../services/user.service";
import {User} from "../../models/user.model";


@Component({
  selector: 'app-show-event',
  templateUrl: './show-event.component.html',
  styleUrls:[
    '../../../assets/css/material-dashboard.css',
    '../../../assets/css/nucleo-icons.css',
    '../../../assets/css/nucleo-svg.css',
    '../../../styles.css'
  ]
})
export class ShowEventComponent {
  eventId: number = 0;
  event: Event | undefined;
  user: any;
  eventComments: Comment[] = [];
  newComment: string = '';
  showCommentSection: boolean = false;
  isUser: boolean = false;
  nickname: string = '';

  constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
    private commentService: CommentService,
    private eventService: EventService,
    private router:Router,
    private userService: UserService,
  ) { }
  ngOnInit(): void{
    this.route.params.subscribe(params => {
      this.eventId = params['id'];
      this.loadEvent();
      this.loadComments();
    });
    this.userService.getCurrentUser().subscribe(
      (currentUser: User) => {
        this.isUser = currentUser.roles.indexOf('USER') > -1;
        this.nickname = currentUser.NICK;
      }
    )
  }

  loadEvent() {
    this.eventService.getEventById(this.eventId).subscribe(
      data => {
        this.event = data;
      }
    );
  }

  loadComments() {
    this.commentService.getCommentsByEventId(this.eventId).subscribe(
      data => {
        this.eventComments = data;
      }
    )
  }
  addComment() {
    const comment = {
      id:0,
      description: this.newComment,
      nick: this.nickname,
      event:this.event
    };

    this.commentService.addCommentForEvent(this.eventId, comment).subscribe(
      () => {
        this.loadComments();
        this.newComment = '';
      }
    );
  }

  navigateToInscription(): void {
    const eventId = this.event?.id;
    if (eventId) {
      this.router.navigate(['/inscription'], { queryParams: { id: eventId } });
    }
  }

  toggleCommentSection(): void {
    this.showCommentSection = !this.showCommentSection;
  }
}
