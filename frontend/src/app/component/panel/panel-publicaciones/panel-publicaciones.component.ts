import { Component, OnInit } from '@angular/core';
import { PublicationResponseDto } from 'src/app/dtos/PublicationResponseDto';
import { PublicationService } from 'src/app/services/publications/publication.service';
import { UserService } from 'src/app/services/users/user.service';

@Component({
  selector: 'app-panel-publicaciones',
  templateUrl: './panel-publicaciones.component.html',
  styleUrls: ['./panel-publicaciones.component.css', '../panel.component.css']
})
export class PanelPublicacionesComponent implements OnInit {

  htmlToAdd: String;

  publicaciones: PublicationResponseDto[];

  constructor(
    private publicationSrv: PublicationService,
    private userSrv: UserService
  ) { }

  ngOnInit(): void {
    this.obtenerPublicaciones();
  }

  public obtenerPublicaciones() {
    this.publicationSrv.get_all_publication().subscribe(
      (response) => {
        this.publicaciones = response;
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
