import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PageComponent } from './page/page.component';
import {LoginComponent} from "./templates/login/login.component";


const routes: Routes = [
  { path:'', component: PageComponent },
  {path:'loginPage',component: LoginComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
