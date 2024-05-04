import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Event } from '../models/event.model';
import { DomSanitizer } from '@angular/platform-browser';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor(private http: HttpClient, private sanitizer: DomSanitizer) { }

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

  public getImage(eventId: number): Observable<any> {
    const url = `/api/events/${eventId}/image`;
    return this.http.get(url, { responseType: 'blob' }).pipe(
      map(blob => this.sanitizer.bypassSecurityTrustUrl(URL.createObjectURL(blob)))
    );

  }

  uploadImage(id:number,file:FormData){
    return this.http.post(`/api/events/${id}/image`,file);
  }

  updateEvent(id:number, event:Event): Observable<any>{
    return this.http.put(`/api/events/${id}`, event);
  }

  deleteEvent( id: number ): Observable<any>{
    return this.http.delete(`/api/events/${id}`);
  }

}
