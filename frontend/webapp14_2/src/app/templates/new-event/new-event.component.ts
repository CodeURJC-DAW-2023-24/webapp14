import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Event } from '../../models/event.model';
import { EventService } from '../../services/event.service';

@Component({
  selector: 'app-new-event',
  templateUrl: './new-event.component.html',
  styleUrls: ['./new-event.component.css']
})
export class NewEventComponent {
  newEvent: Event = {
    id: 0,
    title: '',
    description: '',
    place: '',
    date: '',
    duration: '',
    comments: [],
    imageUrl: '',
  };
  imageFile: File | null = null;

  constructor(private eventService: EventService, private router: Router) { }

  submitForm() {
    if (this.newEvent) {
      this.eventService.createEvent(this.newEvent).subscribe(
        () => {
          console.log('Evento creado con éxito');
          this.router.navigate(['/']); // Redirigir a la página de inicio
        }
      );
    } else {
      console.error('Formulario inválido');
    }
  }

  handleImageInput(event: any) {
    if (event.target.files && event.target.files.length > 0) {
      this.imageFile = event.target.files[0];
    }
  }
}
