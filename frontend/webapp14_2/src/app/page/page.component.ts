import { Component } from '@angular/core';
import { EventService } from '../services/event.service';
import { Event } from '../models/event.model';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-page',
  templateUrl: './page.component.html',
  styleUrl: './page.component.css'
})
export class PageComponent {
  constructor( private eventService: EventService) {}


  events: Event[] = [];

  ngOnInit(): void {
    this.eventService.getEvents().subscribe(
      (events: Event[]) => {
     this.events = events;
    })
  }


}
