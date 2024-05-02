import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user.model';
import { Event } from '../../models/event.model';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
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
export class ProfileComponent implements OnInit {
  user: User | undefined;
  userEvents: Event[] = [];


  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.getCurrentUser();
  }

  getCurrentUser(): void {
    this.userService.getCurrentUser().subscribe((user: User) => {
      this.user = user;
      if (user && user.id) {
        this.userService.getUserEvents(user.id).subscribe((events: Event[]) => {
          this.userEvents = events;
        });
      }
    });
  }
}
