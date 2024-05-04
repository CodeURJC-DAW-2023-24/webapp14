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
  imageFile: string = '';

  constructor(
    private route: ActivatedRoute,
    private eventService: EventService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const eventId = params['id'];
      this.eventService.getEventById(eventId).subscribe(event => {
        this.event = event;

        if (this.event) {
          this.titleEvent = this.event.title;
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
      const updatedEvent: Event = {
        id: this.event.id,
        title: this.titleEvent,
        date: this.dateEvent,
        duration: this.durationEvent,
        place: this.placeEvent,
        description: this.descriptionEvent,
        comments: this.event.comments,
        imageUrl: this.event.imageUrl // Mantén la URL de la imagen actual
      };

      if (this.imageFile) {
        const formData = new FormData();
        formData.append('imageFile', this.imageFile);
        this.eventService.uploadImage(this.event.id, formData).subscribe((response: any) => {
          const imageUrl = response.imageUrl; // Obtener la URL de la imagen del objeto de respuesta
          this.updateEvent(updatedEvent, imageUrl);
        });
      } else {
        this.updateEvent(updatedEvent);
      }
    }
  }

  onFileSelected(event: any) {
    this.imageFile = event.target.files[0];
  }

  private updateEvent(updatedEvent: Event,imageUrl?: string) {
    if (imageUrl) {
      updatedEvent.imageUrl = imageUrl;
    }
    this.eventService.updateEvent(updatedEvent.id, updatedEvent).subscribe(() => {
      this.router.navigate(['/']);
    });
  }
}
