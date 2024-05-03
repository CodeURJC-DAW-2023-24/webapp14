import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user.model';
import { Event } from '../../models/event.model';
import { Router} from '@angular/router';

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
  isEditor: boolean = false;
  isAdmin: boolean = false;


  constructor(private userService: UserService, private router: Router) {}

  ngOnInit(): void {
    this.getCurrentUser();
  }

  getCurrentUser(): void {
    this.userService.getCurrentUser().subscribe((user: User) => {
      this.user = user;
      this.isEditor = user.editor;
      this.isAdmin = user.roles.indexOf('ADMIN') > -1;
      if (user && user.id) {
        this.userService.getUserEvents(user.id).subscribe((events: Event[]) => {
          this.userEvents = events;
        });
      }
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
