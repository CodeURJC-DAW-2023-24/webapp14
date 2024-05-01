import { Component } from '@angular/core';
import { User } from "../../models/user.model";
import { UserService } from "../../services/user.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  nikename: string = '';
  nameUser: string = '';
  surnameUser: string = '';
  emailUser: string = '';
  passwordUser: string = '';
  studyCenterUser: string = '';
  phoneUser: number = 0;

  constructor(private userService: UserService, private router: Router) { }

  onSubmit() {
    // Creamos el objeto de usuario con los datos del formulario
    const dataUser = {
      id: 0,
      name: this.nameUser,
      surname: this.surnameUser,
      email: this.emailUser,
      encodedPassword: this.passwordUser,
      roles: ['USER'],
      studyCenter: this.studyCenterUser,
      phone: this.phoneUser,
      editor: false,
      events: [],
      NICK: this.nikename
    };

    // Llamamos al servicio para registrar al nuevo usuario
    this.userService.doRegister(dataUser).subscribe(
      response => {
        // Redirigimos al usuario a la página de inicio de sesión después del registro exitoso
        this.router.navigate(['/']);
      }
    );
  }
}
