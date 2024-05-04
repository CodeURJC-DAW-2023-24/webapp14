import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageComponent } from './page/page.component';
import {LoginComponent} from "./templates/login/login.component";
import {RegisterComponent} from "./templates/register/register.component";
import {ReviewComponent} from "./templates/review/review.component";
import {DashboardComponent} from "./templates/dashboard/dashboard.component";
import {ProfileComponent} from "./templates/profile/profile.component";
import {EventsComponent} from "./templates/events/events.component";
import {UserPermissionsComponent} from "./templates/user-permissions/user-permissions.component";
import {ShowEventComponent} from "./templates/show-event/show-event.component";
import {InscriptionComponent} from "./templates/inscription/inscription.component";
import {EditFormComponent} from "./templates/edit-form/edit-form.component";
import {EditEventComponent} from "./templates/edit-event/edit-event.component";
import {NewEventComponent} from "./templates/new-event/new-event.component";
import {ErrorComponent} from "./templates/error/error.component";


const routes: Routes = [
  { path:'', component: PageComponent },
  { path:'loginPage',component: LoginComponent },
  { path: 'registerPage', component: RegisterComponent },
  {path:'reviewPage', component:ReviewComponent},
  { path:'dashboardPage', component: DashboardComponent },
  { path:'profilePage', component: ProfileComponent },
  { path:'eventsPage', component: EventsComponent },
  { path:'permissionPage', component: UserPermissionsComponent },
  { path: 'event/:id', component: ShowEventComponent },
  { path: 'inscriptionPage', component: InscriptionComponent},
  {path:'review', component:ReviewComponent},
  { path: 'event/:id', component: ShowEventComponent },
  {path: 'editForm/:id', component: EditFormComponent},
  {path: 'editEvent/:id', component: EditEventComponent},
  {path: 'newEventPage', component: NewEventComponent},
  {path: 'error', component: ErrorComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
