import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/dtos/User.model';
import { UserService } from 'src/app/services/users/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  usuario: String;
  clave: String;
  claveRepetir: String;
  nombre: String;
  apellido: String;
  mail: String;


  user: User;
  htmlToAdd: String;

  constructor(
    private usuariosSrv: UserService,
  ) { }

  ngOnInit(): void {
    this.user = this.usuariosSrv.getUserLoggedIn();
  }

  public registrarUsuario() {
    if (this.usuario == null || this.clave == null ||
      this.claveRepetir == null || this.nombre == null ||
      this.apellido == null || this.mail == null) {
      this.htmlToAdd = '<p>Campos vacios<p>';
    } else {
      if (this.usuario.length < 8) {
        this.htmlToAdd = '<p>El usuario debe tener como minimo 8 caracteres.<p>';
      } else if (this.clave.length < 8) {
        this.htmlToAdd = '<p>La clave debe tener como minimo 8 caracteres.<p>';
      } else if (this.claveRepetir.length < 8) {
        this.htmlToAdd = '<p>La clave debe tener como minimo 8 caracteres.<p>';
      } else if (!this.mail.includes("@")) {
        this.htmlToAdd = '<p>El e-mail no es valido.<p>';
      }else{
        console.log("LLAMAMOS AL BACKEND");
      }
    }
  }

}
