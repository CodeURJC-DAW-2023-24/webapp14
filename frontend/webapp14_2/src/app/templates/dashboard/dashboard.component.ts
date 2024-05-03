import { Component, OnInit } from '@angular/core';
import { User } from "../../models/user.model";
import { UserService } from "../../services/user.service";
import { EventService } from "../../services/event.service";
import { Event } from "../../models/event.model";
import { Chart } from 'chart.js/auto';
import { forkJoin } from 'rxjs';
import { Router} from "@angular/router";

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
export class DashboardComponent implements OnInit {
  numUsers: number = 0;
  numEvents: number = 0;
  countTecnologias: number = 0;
  countArtes: number = 0;
  countSanitarios: number = 0;
  countHumanidades: number = 0;
  dataLoaded: boolean = false;
  isAdmin: boolean = false;
  isEditor: boolean = false;

  constructor(private userService: UserService, private eventService: EventService, private router: Router) {}

  ngOnInit(): void {
    forkJoin([
      this.eventService.getEvents(),
      this.userService.getUsers(),
      this.eventService.getTecnologia(),
      this.eventService.getArte(),
      this.eventService.getSanitario(),
      this.eventService.getHumanidades()
    ]).subscribe(
      ([events, users, tecnologia, arte, sanitario, humanidades]: [Event[], User[], number, number, number, number]) => {
        this.numEvents = events.length;
        this.numUsers = users.length;
        this.countTecnologias = tecnologia;
        this.countArtes = arte;
        this.countSanitarios = sanitario;
        this.countHumanidades = humanidades;
        this.dataLoaded = true;
        this.initializeChart();
      }
    );
  }

  initializeChart(): void {
    if (!this.dataLoaded) return;

    // Calcular los datos para el gráfico de quesitos
    const labels = ['Tecnología', 'Artes', 'Sanitario', 'Humanidades'];
    const data = [this.countTecnologias, this.countArtes, this.countSanitarios, this.countHumanidades];

    // Obtener el elemento canvas
    const ctxPie = document.getElementById("chart-pie") as HTMLCanvasElement;

    if (ctxPie) {
      new Chart(ctxPie, {
        type: "pie",
        data: {
          labels: labels,
          datasets: [{
            label: "Eventos por Categoría",
            data: data,
            backgroundColor: [
              'rgb(204,165,165)',
              'rgb(91,57,57)',
              'rgb(145,49,49)',
              'rgb(73,9,9)'
            ],
            borderColor: [
              'rgb(204,165,165)',
              'rgb(91,57,57)',
              'rgb(145,49,49)',
              'rgb(73,9,9)'
            ],
            borderWidth: 1
          }]
        },
        options: {
          responsive: true,
          maintainAspectRatio: false
        }
      });
    }
    this.userService.getCurrentUser().subscribe(
      (user: User) => {
        this.isEditor = user.editor;
        this.isAdmin = user.roles.indexOf('ADMIN') > -1;
      }
    )
  }

  goToIndex(){
    this.router.navigate(['/']);
  }

  goToDashboard(){
    this.router.navigate(['/dashboardPage']);
  }

  goToReview() {
    this.router.navigate(['/reviewPage']);
  }

  goToUserPermissions(){
    this.router.navigate(['/permissionPage']);
  }

  goToProfile(){
    this.router.navigate(['/profilePage']);
  }

  goToEvents(){
    this.router.navigate(['/eventsPage']);
  }

}
