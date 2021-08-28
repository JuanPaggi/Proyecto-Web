import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/dtos/User.model';
import { UsuarioDatosDto } from 'src/app/dtos/UsuarioDatosDto';
import { UsuarioLoginDto } from 'src/app/dtos/UsuarioLoginDto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  public usserLogged: User;

  private headers = { withCredentials: true };

  constructor(private http: HttpClient) {
  }

  public verify_user(body: UsuarioLoginDto): Observable<boolean> {
    return this.http.post<boolean>(
      environment.apiEndpoint + '/usuarios/login',
      body,
      this.headers
    );
  }

  public get_user(): Observable<UsuarioDatosDto> {
    return this.http.get<UsuarioDatosDto>(
      environment.apiEndpoint + '/usuarios',
      this.headers
    );
  }

  //Metodos para la sesion

  setUserLoggedIn(user: User) {
    this.usserLogged = user;
    localStorage.setItem('currentUser', JSON.stringify(user));
  }

  setUserLoggedOut() {
    this.usserLogged = null;
    localStorage.setItem('currentUser', JSON.stringify(null));
  }

  getUserLoggedIn() {
    return JSON.parse(localStorage.getItem('currentUser'));
  }

}
