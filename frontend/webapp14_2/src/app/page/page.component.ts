import { Component } from '@angular/core';
import { EventService } from '../services/event.service';
import { UserService } from '../services/user.service';
import { Event } from '../models/event.model';
import { User } from '../models/user.model';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-page',
  templateUrl: './page.component.html',
  styleUrls: ['./page.component.css']
})
export class PageComponent {
  constructor(private eventService: EventService, private userService: UserService, private router: Router) {}

  events: Event[] = [];
  currentUser: User | undefined;
  isUser: boolean = false;
  isAdmin: boolean = false;
  page: number = 0;
  pageSize: number = 10;

  ngOnInit(): void {
    this.loadEvents();
    this.userService.getCurrentUser().subscribe(
      (currentUser: User) => {
        this.currentUser = currentUser;
        if (this.currentUser) {
          this.isUser = true;
          this.isAdmin = (this.currentUser.roles.indexOf('ADMIN') > -1);
        }
      }
    );
  }

  loadEvents(): void {
    this.eventService.loadEvents(this.page, this.pageSize).subscribe(
      (events: Event[]) => {
        this.events = this.events.concat(events);
        this.page++;
      }
    );
  }

  goToLogin(): void {
    this.router.navigate(['/loginPage']);
  }

  goToDashboard(): void {
    this.router.navigate(['/dashboardPage']);
  }
}
