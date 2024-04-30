import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageComponent } from './templates/page/page.component';
import {ReviewComponent} from "./templates/review/review.component";


const routes: Routes = [
  { path:'', component: PageComponent },
  {path:'comments', component: ReviewComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
