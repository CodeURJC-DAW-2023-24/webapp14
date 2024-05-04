import { Component, NgModule } from '@angular/core';
import {Comment} from "../../models/comment.model";
import {CommentService} from "../../services/comment.service";
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";
import {User} from "../../models/user.model";
import {EventService} from "../../services/event.service";
import { Event} from "../../models/event.model";

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: [
    '../../../assets/css/nucleo-icons.css',
    '../../../assets/css/material-dashboard.css',
    '../../../assets/css/nucleo-svg.css',
    '../../../assets/css/styles.css',
    '../../../assets/css/styles-showEvent.css',
    '../../../assets/css/stylesInsc.css',
    '../../../assets/css/styles-error.css',
  ]
})
export class EventsComponent {
  eventsShow: Event[] = [];
  isAdmin:boolean = false;
  isEditor:boolean = false;
  title: string= '';
  place: string = '';
  date: string = '';
  duration: string = '';
  description: string = '';

  constructor(private eventsService: EventService, private userService: UserService, private router: Router) {}

  ngOnInit(): void {
    this.userService.getCurrentUser().subscribe(
      (user:User) => {
        this.isEditor = user.editor;
        this.isAdmin = user.roles.indexOf('ADMIN') > -1;
      }
    )
    this.eventsService.getEvents().subscribe(
      (events: Event[]) => {
        this.eventsShow = events;
      }
    )

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

  goToEditEvent(id: number){
    this.router.navigate(['editEvent', id]);
  }

  goToNewEvent(){
    this.router.navigate(['/newEventPage']);
  }

  deleteEvent(id: number){
    this.eventsService.deleteEvent(id).subscribe(
      (data: Event) => {
        window.location.reload();
      }
    )
  }

}
