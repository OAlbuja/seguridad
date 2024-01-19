//user.service.ts
import { Injectable, OnInit } from '@angular/core';
// import { users } from './user.json'; ya no se usa los datos quemados
import { User } from './user';
import { Observable } from 'rxjs'; //Se importa observable para invocarlo en el metodo y tener el centinela
import { of } from 'rxjs'; //Of para el return y sea observable
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { map } from 'rxjs';
import { response } from 'express';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private urlEndPoint: string = 'http://localhost:8080/api/usuarios';
  private httpHeaders = new HttpHeaders ({'Content-Type': 'application/json'})

  constructor(private http: HttpClient) { }

  getUser (): Observable<User[]> { //aplicando el centinela
    // return of (users); //Aplicando el of
    return this.http.get(this.urlEndPoint).pipe(
      map(response => response as User [])
    );
  }

  create(user: User) : Observable <User> {
    return this.http.post<User>(this.urlEndPoint, user, {headers: this.httpHeaders})

  }

  update(user: User) : Observable <User> {
    return this.http.put<User>(`${this.urlEndPoint}/${user.id}`, user, {headers: this.httpHeaders})

  }

  delete (id: number): Observable <User> {
    return this.http.delete<User>(`${this.urlEndPoint}/${id}`,{headers: this.httpHeaders})
  }
}
