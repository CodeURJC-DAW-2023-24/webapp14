import { Component } from '@angular/core';
import {ActivatedRoute,Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {CommentService} from "../../services/comment.service";
import {EventService} from "../../services/event.service";
import {Comment} from "../../models/comment.model";


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
  eventId!: number;
  event!: Event;
  user: any;
  eventComments: Comment[] = [];
  newComment: string = '';
  showCommentSection: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
    private commentService: CommentService,
    private eventService: EventService,
    private router:Router
  ) { }
  ngOnInit(): void{
    this.route.params.subscribe(params => {
      this.eventId = params['id'];
      this.loadEvent();
      this.loadComments();
    });
  }

  loadEvent() {
    this.eventService.getEventById(this.eventId).subscribe(
      data => {
        this.event = data;
      },
      error => {
        console.log('Error loading event:', error);
      }
    );
  }

  loadComments() {
    this.commentService.getCommentsByEventId(this.eventId).subscribe(
      data => {
        this.eventComments = data;
      },
      error => {
        console.log('Error loading comments:', error);
      }
    )
  }
  addComment() {
    const comment: Comment = {
      id: 0,
      description: this.newComment,
      nick: 'user',
      event: this.event
    };

    // Llama al método addCommentForEvent con el objeto Comment completo
    this.commentService.addCommentForEvent(this.eventId, comment).subscribe(
      () => {
        this.loadComments();
        this.newComment = ''; // Limpiar el campo de entrada después de agregar el comentario
      },
      error => {
        console.log('Error adding comment:', error);
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
