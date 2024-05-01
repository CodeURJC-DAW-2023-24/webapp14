import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PageComponent } from './page/page.component';
import { DashboardComponent } from './templates/dashboard/dashboard.component';
import { EditEventComponent } from './templates/edit-event/edit-event.component';
import { EditFormComponent } from './templates/edit-form/edit-form.component';
import { ErrorComponent } from './templates/error/error.component';
import { EventsComponent } from './templates/events/events.component';
import { InscriptionComponent } from './templates/inscription/inscription.component';
import { LoginComponent } from './templates/login/login.component';
import { NewEventComponent } from './templates/new-event/new-event.component';
import { ProfileComponent } from './templates/profile/profile.component';
import { RegisterComponent } from './templates/register/register.component';
import { ReviewComponent } from './templates/review/review.component';
import { ShowEventComponent } from './templates/show-event/show-event.component';
import { UserPermissionsComponent } from './templates/user-permissions/user-permissions.component';

import { HttpClientModule } from '@angular/common/http';
import {FormsModule} from '@angular/forms';


@NgModule({
  declarations: [
    AppComponent,
    PageComponent,
    DashboardComponent,
    EditEventComponent,
    EditFormComponent,
    ErrorComponent,
    EventsComponent,
    InscriptionComponent,
    LoginComponent,
    NewEventComponent,
    ProfileComponent,
    RegisterComponent,
    ReviewComponent,
    ShowEventComponent,
    UserPermissionsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
