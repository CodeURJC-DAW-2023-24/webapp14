import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TicketService } from '../../services/ticket.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EventService } from '../../services/event.service'; // Importa el servicio de eventos
import jsPDF from 'jspdf';
import {User} from "../../models/user.model";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css']
})
export class InscriptionComponent implements OnInit {
  ticketForm: FormGroup;
  eventId: number = 0;
  userId: number = 0;
  eventName: string | undefined; // Agrega una variable para almacenar el nombre del evento

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private ticketService: TicketService,
    private eventService: EventService,
    private userService: UserService,
    private formBuilder: FormBuilder
  ) {
    this.ticketForm = this.formBuilder.group({
      name: ['', Validators.required],
      surname: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', Validators.required],
      id: [''] // No necesitas definir el ID aquí, se establecerá en ngOnInit()
    });
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.eventId = +params['id']; // Convertir a número
      this.ticketForm.controls['id'].setValue(this.eventId);
      this.getEventTitle(); // Obtener el título del evento cuando se inicializa el componente
    });
  }

  getEventTitle() {
    if (this.eventId) {
      this.eventService.getEventById(this.eventId).subscribe(
        (event) => {
          this.eventName = event.title; // Almacena el título del evento
        },
        (error) => {
          console.error('Error al obtener el título del evento:', error);
        }
      );
    }
  }

  submitForm() {
    if (this.ticketForm.valid) {
      // Obtener userId
      this.userService.getCurrentUser().subscribe(
        (currentUser: User) => {
          this.userId = currentUser.id;
          // Asignar userId al formulario
          this.ticketForm.patchValue({ userId: this.userId });
          // Crear el ticket dentro del callback de getCurrentUser
          this.createTicket();
        },
        error => {
          console.error('Error al obtener el usuario:', error);
        }
      );
    } else {
      console.error('Formulario inválido');
    }
  }

  createTicket() {
    // Crear el ticket
    this.ticketService.createTicket(this.ticketForm.value, this.userId, this.eventId).subscribe(
      () => {
        console.log('Ticket creado con éxito');
        this.generatePdf(this.ticketForm.value);
        this.router.navigate(['/']);
      },
      error => {
        console.error('Error al crear el ticket:', error);
      }
    );
  }



  generatePdf(ticketData: any) {
    const doc = new jsPDF();
    doc.text('Detalles de Inscripción', 10, 10);
    doc.setFont('helvetica', 'normal');
    doc.setFontSize(12);
    doc.text(`Nombre del evento: ${this.eventName}`, 10, 20);
    doc.text(`Nombre: ${ticketData.name}`, 10, 30);
    doc.text(`Apellido: ${ticketData.surname}`, 10, 40);
    doc.text(`Email: ${ticketData.email}`, 10, 50);
    doc.text(`Teléfono: ${ticketData.phone}`, 10, 60);
    doc.save('inscription_details.pdf');
  }

}
