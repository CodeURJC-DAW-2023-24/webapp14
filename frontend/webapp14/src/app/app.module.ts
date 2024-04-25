import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {EventsComponent} from './components/events/events.component';
import {UsersComponent} from './components/users/users.component';
import {CommentsComponent} from './components/comments/comments.component';
import {TicketsComponent} from  './components/tickets/tickets.component';
import {HttpClientModule} from  '@angular/common/http';
import { AppComponent } from './app.component';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    EventsComponent,
    UsersComponent,
    CommentsComponent,
    TicketsComponent,
    HttpClientModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
