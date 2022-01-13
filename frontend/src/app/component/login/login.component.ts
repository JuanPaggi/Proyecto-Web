import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/dtos/User.model';
import { UsuarioDatosDto } from 'src/app/dtos/UsuarioDatosDto';
import { UsuarioLoginDto } from 'src/app/dtos/UsuarioLoginDto';
import { UserService } from 'src/app/services/users/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  usuario: String;
  clave: String;

  usuarioDatos: UsuarioDatosDto = new UsuarioDatosDto();
  user: User;

  htmlToAdd: String;

  constructor(
    private usuariosSrv: UserService
  ) {
  }

  ngOnInit(): void {
    this.user = this.usuariosSrv.getUserLoggedIn();
  }

  ComprobarUsuario() {
    let login = new UsuarioLoginDto();
    login.user = this.usuario;
    login.clave = this.clave;
    this.usuariosSrv.verify_user(login).subscribe(
      (response) => {
        if (response) {
          this.usuariosSrv
            .get_user()
            .subscribe((response) => {
              console.log(response);
              this.usuarioDatos = response;
              this.logIn(response.user, response.nombre, response.apellido, response.id_usuario, response.admin);
              window.location.href = '/event';
            });
        } else {
          this.htmlToAdd = '<p>Datos Incorrectos<p>';
        }
      },
      (err) => {
        switch (err.status) {
          case 401:
            this.htmlToAdd = '<p class="text-danger">El usuario o la clave son invalidas.</p>';
            break;
          case 500:
            this.htmlToAdd = '<p class="text-danger">Error en el servidor.</p>';
        }
      }
    );
  }

  logIn(user: String, nombre: String, apellido: String, idUsuario: number, admin:boolean) {
    let u: User = { user, nombre, apellido, idUsuario, admin };
    this.usuariosSrv.setUserLoggedIn(u);
  }

}
