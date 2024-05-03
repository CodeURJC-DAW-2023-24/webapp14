import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Event } from '../models/event.model';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor(private http: HttpClient) { }

  getEvents(): Observable<Event[]> {
    return this.http.get<Event[]>(`/api/events/`);
  }

  getEventById(id:number): Observable<Event>{
    return this.http.get<Event>(`/api/events/${id}`);
  }

  loadEvents(page: number, pageSize: number): Observable<Event[]> {
    const url = `/api/events/pageableEvents?page=${page}&size=${pageSize}`;
    return this.http.get<Event[]>(url);
  }

  getTecnologia(): Observable<number>{
    return this.http.get<number>(`/api/events/tecnology`);
  }

  getArte(): Observable<number>{
    return this.http.get<number>(`/api/events/art`);
  }

  getSanitario(): Observable<number>{
    return this.http.get<number>(`/api/events/helth`);
  }

  getHumanidades(): Observable<number>{
    return this.http.get<number>(`/api/events/human`);
  }

}
