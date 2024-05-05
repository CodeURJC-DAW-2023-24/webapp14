import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../../services/user.service";
import {User} from "../../models/user.model";

@Component({
  selector: 'app-edit-form',
  templateUrl: './edit-form.component.html',
  styleUrls:[
    '../../../assets/css/stylesInsc.css'
  ]
})
export class EditFormComponent {
  user: User | undefined;
  nickUser: string = '';
  nameUser: string = '';
  surnameUser: string = '';
  emailUser: string = '';
  studyCenterUser: string = '';
  phoneUser: number = 0;
  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private router : Router
  ) {
  }
  ngOnInit():void{
    this.route.params.subscribe(params => {
      const userId = params['id'];
      this.userService.getUserById(userId).subscribe(user => {
        this.user = user;

        if (this.user){
          this.nameUser=this.user.name;
          this.surnameUser = this.user.surname;
          this.nickUser = this.user.NICK;
          this.emailUser = this.user.email;
          this.studyCenterUser = this.user.studyCenter;
          this.phoneUser = this.user.phone;
        }
      });
    });
  }

  onSubmit() {
    if (this.user) {
      const dataUser: User = {
        id: this.user.id,
        name: this.nameUser,
        surname: this.surnameUser,
        email: this.emailUser,
        encodedPassword: this.user.encodedPassword,
        roles: this.user.roles,
        studyCenter: this.studyCenterUser,
        phone: this.phoneUser,
        editor: this.user.editor,
        events: this.user.events,
        NICK: this.nickUser
      };
      this.userService.updateUser(dataUser.id, dataUser).subscribe(() =>
      {
        this.router.navigate(['/profilePage']);
      }
      )
    };
  }
}
