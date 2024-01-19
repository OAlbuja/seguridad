//app-routing.module.ts

import { NgModule } from '@angular/core';
//Importacion de Componentes
import { UserComponent } from './user/user.component';
import { ContactoComponent } from './contacto/contacto.component';
import { UserCreateFormComponent } from './user/user-create-form/user-create-form.component';
//Importacion de rutas para los componentes
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';

//Arreglo de rutas
const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'user', component: UserComponent,
  children: [
    { path: 'userCreateForm', component: UserCreateFormComponent }
  ]
  },
  {path: 'contacto', component: ContactoComponent},
  {path: 'login', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

