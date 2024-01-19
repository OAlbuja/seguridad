//user-create-form.component.ts

import { Component } from '@angular/core';
import { User } from '../user';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user-create-form',
  templateUrl: './user-create-form.component.html',
  styleUrl: './user-create-form.component.css'
})
export class UserCreateFormComponent {

  public user: User = new User();
  public titulo: String = 'Crear Usuario';

  constructor(private _route: ActivatedRoute) { }


  ngOnInit(): void {
    this._route.params.subscribe(params => {
      console.log(params[''])
    });
  }

  public create(): void {
    console.log("Clicked!")
    console.log(this.user)
  }

}
