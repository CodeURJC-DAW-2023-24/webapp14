import { Component } from '@angular/core';
import {Event} from "../../models/event.model";
import {ActivatedRoute, Router} from "@angular/router";
import {EventService} from "../../services/event.service";


@Component({
  selector: 'app-edit-event',
  templateUrl: './edit-event.component.html',
  styleUrls: ['../../../assets/css/stylesInsc.css']
})
export class EditEventComponent {
  event: Event | undefined;
  titleEvent: string = '';
  dateEvent: string = '';
  durationEvent: string = '';
  placeEvent: string = '';
  descriptionEvent: string = '';

  constructor(
    private route: ActivatedRoute,
    private eventService:EventService,
    private router: Router
    ) {
  }

  ngOnInit():void{
    this.route.params.subscribe( params => {
      const eventId = params['id'];
      this.eventService.getEventById(eventId).subscribe( event => {
        this.event = event;

        if (this.event){
          this.titleEvent=this.event.title;
          this.dateEvent = this.event.date;
          this.durationEvent = this.event.duration;
          this.placeEvent = this.event.place;
          this.descriptionEvent = this.event.description;
        }
      });
    });
  }

  onSubmit() {
    if (this.event) {
      const dataEvent: Event = {
        id: this.event.id,
        title: this.titleEvent,
        description: this.descriptionEvent,
        place: this.placeEvent,
        date: this.dateEvent,
        duration:this.durationEvent,
        imageUrl:this.event.imageUrl,
        comments:this.event.comments
      };
      this.eventService.updateEvent(dataEvent.id, dataEvent).subscribe(() =>
        {
          this.router.navigate(['/']);
        }
      )
    };
  }
}
