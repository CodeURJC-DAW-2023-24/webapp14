import { Component, OnInit } from '@angular/core';
import { UserService } from "../../services/user.service";
import { User } from "../../models/user.model";
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-permissions',
  templateUrl: './user-permissions.component.html',
  styleUrls: [
    '../../../../node_modules/bootstrap/dist/css/bootstrap.min.css'
  ]
})
export class UserPermissionsComponent implements OnInit {
  constructor(private userService: UserService, private router: Router) {}

  users: User[] = [];
  isAdmin: boolean = false;
  isEditor: boolean = false;

  ngOnInit(): void {
    this.getCurrentUser();
    this.fetchUsers();

  }

  getCurrentUser(): void {
    this.userService.getCurrentUser().subscribe((user: User) => {
      this.isEditor = user.editor;
      this.isAdmin = user.roles.indexOf('ADMIN') > -1;
    });
  }

  fetchUsers(): void {
    this.userService.getUsers().subscribe(
      (users: User[]) => {
        this.users = users;
      },
      error => {
        console.error('Error fetching users:', error);
      }
    );
  }

  goToIndex(){
    this.router.navigate(['/']);
  }

  goToDashboard(){
    this.router.navigate(['/dashboardPage']);
  }

  goToReview() {
    this.router.navigate(['/reviewPage']);
  }

  goToUserPermissions(){
    this.router.navigate(['/permissionPage']);
  }

  goToProfile(){
    this.router.navigate(['/profilePage']);
  }

  goToEvents(){
    this.router.navigate(['/eventsPage']);
  }

  putPermission(id: number){
    this.userService.putPermission(id).subscribe(
      response => {
        console.log('Cambio hecho', response);


      }
    )
  }

  deletePermission(id: number){
    this.userService.quitPermission(id).subscribe(
      response => {
        this.fetchUsers();
        console.log('Cambio hecho', response);
        // Recargar la página



      }
    );
  }



}
