//user.component.ts

import { Component } from '@angular/core';
import { User } from './user';
import { UserService } from './user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
})
export class UserComponent {
  user: User[] = [];

  constructor(private userService: UserService) {} //Se inyecta el servicio en el constructor

  ngOnInit() {
    //se optiene los usuarios a traves del metodo getUsers. y se suscribe para el uso del observable
    this.userService.getUser().subscribe(
      (user) => (this.user = user) //Actualiza el estado del user en angular
    );
  }
}
