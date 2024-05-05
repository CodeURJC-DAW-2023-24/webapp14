import { Component } from '@angular/core';
import {UserService} from "../../services/user.service";
import {User} from "../../models/user.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  user: User | undefined;
  username: string = '';
  pass: string = '';

  constructor(private userService: UserService, private router: Router) {}



  onSubmit(){
    const credentials = {
      username: this.username,
      password: this.pass
    };

    this.userService.doLogin(credentials).subscribe(
      response => {
        console.log('Inicio exitoso', response);
        this.router.navigate(['/']);
      }
    )
  }

  goToRegister(): void {
    this.router.navigate(['/registerPage']);
  }
}
