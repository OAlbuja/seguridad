//app.module.ts
import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

//Importacion de componentes
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { ContactoComponent } from './contacto/contacto.component';
import { UserComponent } from './user/user.component';
import { HomeComponent } from './home/home.component';

//Importacion de Servicios
import { UserService } from './user/user.service';
import { ContactoService } from './contacto/contacto.service';
import { FormsModule } from '@angular/forms';
import { UserCreateFormComponent } from './user/user-create-form/user-create-form.component';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login/login.component';

//interceptors

import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from './interceptor/auth.interceptor';



@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    ContactoComponent,
    UserComponent,
    HomeComponent,
    UserCreateFormComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [
    provideClientHydration(),
    UserService,
    ContactoService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
