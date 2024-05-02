import { Component } from '@angular/core';
import {User} from "../../models/user.model";
import {UserService} from "../../services/user.service";
import {EventService} from "../../services/event.service";
import { Event } from "../../models/event.model";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [
    '../../../assets/css/material-dashboard.css',
    '../../../assets/css/nucleo-icons.css',
    '../../../assets/css/nucleo-svg.css',
    '../../../assets/scss/material-dashboard.scss',
    ]
})
export class DashboardComponent {
  numUsers: number = 0;
  numEvents: number = 0;
  countTecnologias: number = 0;
  countArtes: number = 0;
  countSanitarios: number = 0;
  countHumanidades: number = 0;

  constructor(private userService: UserService, private eventService:EventService) {}

  ngOnInit(): void {
    this.eventService.getEvents().subscribe(
      (events:Event[]) => {
        this.numEvents = events.length;
      }
    )

    this.userService.getUsers().subscribe(
      (users:User[]) => {
        this.numUsers = users.length;
      }
    )

    this.eventService.getTecnologia().subscribe(
      (tecnology: number) => {
        this.countTecnologias = tecnology;
      }
    )

    this.eventService.getArte().subscribe(
      (arte: number) => {
        this.countArtes = arte;
      }
    )

    this.eventService.getSanitario().subscribe(
      (sanitario: number) => {
        this.countSanitarios = sanitario;
      }
    )

    this.eventService.getHumanidades().subscribe(
      (humanidades: number) => {
        this.countHumanidades = humanidades;
      }
    )



  }

}
