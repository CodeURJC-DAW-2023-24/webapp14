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
    '../../../../node_modules/bootstrap/dist/css/bootstrap.min.css'
  ]
})
export class ShowEventComponent {
  eventId: number = 0;
  event: Event | undefined;
  eventComments: Comment[] = [];
  newComment: string = '';
  showCommentSection: boolean = false;
  isUser: boolean = false;
  nickname: string = '';
  user: User | undefined;
  imageUrl: string = '';

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
        this.user = currentUser;
        this.nickname = this.user.NICK;
        this.isUser = currentUser.roles.indexOf('USER') > -1;
      }
    )
  }

  loadEvent() {
    this.eventService.getEventById(this.eventId).subscribe(
      data => {
        this.event = data;
      }
    );
    this.eventService.getImage(this.eventId).subscribe(
      imageUrl => {
        this.imageUrl = imageUrl;
      }
    )
  }

  loadComments() {
    this.commentService.getCommentsByEventId(this.eventId).subscribe(
      data => {
        this.eventComments = data;
      }
    )
  }
  addComment() {
    const commentNew = {
      id:this.event?.id,
      description: this.newComment,
      nick: this.nickname,
      event:this.event
    };

    this.commentService.addCommentForEvent(this.eventId, commentNew).subscribe(
      () => {
        //this.loadComments();
      }
    );



  }

  navigateToInscription(): void {
    const eventId = this.event?.id;
    if (eventId) {
      this.router.navigate(['/inscriptionPage'], { queryParams: { id: eventId } });
    }
  }

  toggleCommentSection(): void {
    this.showCommentSection = !this.showCommentSection;
  }
}
