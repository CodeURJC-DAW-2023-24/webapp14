import { Component } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
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
  constructor(
    private route: ActivatedRoute,
    private userService: UserService
  ) {
  }
  ngOnInit():void{
    this.route.params.subscribe(params => {
      const userId = params['id'];
      this.userService.getUserById(userId).subscribe(user => {
        this.user = user;
      });
    });
  }

  onSubmit(){
    if(this.user){
      this.userService.updateUser(this.user.id,this.user).subscribe();
    }
  }
}
