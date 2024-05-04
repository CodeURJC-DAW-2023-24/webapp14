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
  imageField: File | undefined;
  newID: number = 0;

  constructor(private eventService: EventService, private router: Router) {
  }

  submitForm() {
    if (this.newEvent.title && this.newEvent.date && this.newEvent.duration && this.newEvent.place && this.newEvent.description) {
      this.eventService.createEvent(this.newEvent).subscribe(
        (event: Event) => {
          console.log('Evento creado con éxito');
          this.newID = event.id;
          if (this.imageField) {
            const formData = new FormData();
            formData.append('imageFile', this.imageField);
            this.eventService.newImage(this.newID, formData).subscribe(
              (response: any) => {
                console.log('Imagen subida con éxito');
                this.router.navigate(['/']);
              },
              error => {
                console.error('Error al subir la imagen', error);
              }
            );
          } else {
            console.error('No se ha seleccionado ninguna imagen');
            this.router.navigate(['/']);
          }
        },
        error => {
          console.error('Error al crear el evento', error);
        }
      );
    } else {
      console.error('Formulario inválido');
    }
  }

  onFileSelected(event: any) {
    this.imageField = event.target.files[0];
  }

}
