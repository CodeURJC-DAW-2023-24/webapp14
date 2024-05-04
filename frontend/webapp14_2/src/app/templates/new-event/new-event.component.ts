import { Component } from '@angular/core';
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
    category: '',
    n_tickets: 0
  };
  imageFile: File | null = null;

  constructor(private eventService: EventService) { }

  submitForm() {
    if (this.newEvent && this.imageFile) {
      const formData = new FormData();
      formData.append('title', this.newEvent.title);
      formData.append('description', this.newEvent.description);
      formData.append('place', this.newEvent.place);
      formData.append('date', this.newEvent.date);
      formData.append('duration', this.newEvent.duration);
      formData.append('category', this.newEvent.category);
      formData.append('n_tickets', this.newEvent.n_tickets.toString());
      formData.append('imageField', this.imageFile);

      this.eventService.createEvent(formData).subscribe(
        () => {
          console.log('Evento creado con éxito');
          // Redirigir a la página principal u otra página deseada
        },
        error => {
          console.error('Error al crear el evento:', error);
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
