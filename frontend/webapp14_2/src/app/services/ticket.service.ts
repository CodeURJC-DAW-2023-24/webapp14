import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TicketService {
  private baseUrl = '/api/tickets';

  constructor(private http: HttpClient) { }

  getTickets(): Observable<any[]> {
    return this.http.get<any[]>(this.baseUrl);
  }

  getTicketById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createTicket(ticket: any, user: number, event: number): Observable<any> {
    // Extraer solo los campos necesarios para crear el ticket
    const { name, surname, phone, email, eventId, userId } = ticket;
    const ticketData = { name, surname, phone, email, eventId, userId }; // Objeto con datos del ticket
    return this.http.post(this.baseUrl, ticketData, { responseType: 'text' });
  }


  updateTicket(id: number, ticket: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, ticket, { responseType: 'text' });
  }

  deleteTicket(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }
}
