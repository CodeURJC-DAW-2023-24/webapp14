import { Component } from '@angular/core';
import { EventService } from '../services/event.service';
import { UserService } from '../services/user.service';
import { Event } from '../models/event.model';
import { User } from '../models/user.model';

@Component({
  selector: 'app-page',
  templateUrl: './page.component.html',
  styleUrl: './page.component.css'
})
export class PageComponent {
  constructor( private eventService: EventService, private userService: UserService ) {}


  events: Event[] = [];
  currentUser: User | undefined ;
  isUser: boolean = false;
  isAdmin: boolean = false;

  ngOnInit(): void {
    this.eventService.getEvents().subscribe(
      (events: Event[]) => {
     this.events = events;
    })
    this.userService.getCurrentUser().subscribe(
      (currentUser: User) => {
        this.currentUser = currentUser;
      })
    if (this.currentUser != undefined) {
      this.isUser = true;
      for ( var i = 0; i < this.currentUser?.roles.length; i++){
        let rol = this.currentUser.roles[i];
        if (rol == 'admin') {
          this.isAdmin = true;
        }
      }
    }


  }


}
