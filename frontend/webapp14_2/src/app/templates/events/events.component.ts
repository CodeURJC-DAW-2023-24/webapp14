import { Component } from '@angular/core';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent {
  allEvents: any[] = [
    { title: 'Evento 1', id: 1, place: 'Lugar 1', date: '2024-05-01', duration: '2 horas', description: 'Descripción del evento 1' },
    { title: 'Evento 2', id: 2, place: 'Lugar 2', date: '2024-05-02', duration: '3 horas', description: 'Descripción del evento 2' }
    // Agrega más eventos según necesites
  ];
  admin: boolean = true; // O false, dependiendo del rol del usuario
  isUserEditor: boolean = true; // O false, dependiendo del rol del usuario
  token: string = 'your_csrf_token_here'; // Token CSRF obtenido del backend

  constructor() { }
}
