import { Component, OnInit } from '@angular/core';
import { UsuarioDatosDto } from 'src/app/dtos/UsuarioDatosDto';
import { UserService } from 'src/app/services/users/user.service';

@Component({
  selector: 'app-panel-usuarios',
  templateUrl: './panel-usuarios.component.html',
  styleUrls: ['./panel-usuarios.component.css', '../panel.component.css']
})
export class PanelUsuariosComponent implements OnInit {

  htmlToAdd: String;

  users: UsuarioDatosDto[];

  constructor(
    private userSrv: UserService,
  ) { }

  ngOnInit(): void {
    this.obtenerPublicaciones();
  }

  public obtenerPublicaciones() {
    this.userSrv.get_all().subscribe(
      (response) => {
        this.users = response;
      },
      (error) => {
        switch (error.status) {
          case 401:
            this.userSrv.setUserLoggedOut();
            window.location.href = '/';
            break;
          case 404:
            this.htmlToAdd = '<p class="text-danger">No existe.</p>';
            break;
          default:
            this.htmlToAdd = '<p class="text-danger">Error en el servidor.</p>';
        }
      }
    );
  }
}
