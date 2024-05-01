import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageComponent } from './page/page.component';
import {LoginComponent} from "./templates/login/login.component";
import {RegisterComponent} from "./templates/register/register.component";
import {ReviewComponent} from "./templates/review/review.component";


const routes: Routes = [
  { path:'', component: PageComponent },
  { path:'loginPage',component: LoginComponent },
  { path: 'registerPage', component: RegisterComponent },
  {path:'review', component:ReviewComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
