import { Component, OnInit } from '@angular/core';
import { PublicationCreateDto } from 'src/app/dtos/PublicationCreateDto';
import { PublicationService } from 'src/app/services/publications/publication.service';
import { UserService } from 'src/app/services/users/user.service';

@Component({
  selector: 'app-panel-publicaciones-crear',
  templateUrl: './panel-publicaciones-crear.component.html',
  styleUrls: ['./panel-publicaciones-crear.component.css']
})
export class PanelPublicacionesCrearComponent implements OnInit {

  titulo: String
  descripcion: String

  htmlToAdd: String;

  constructor(
    private publicationSrv: PublicationService,
    private userSrv: UserService
  ) { }

  ngOnInit(): void {
  }

  public createPublication() {
    let body = new PublicationCreateDto()
    if (this.titulo == null || this.descripcion == null) {
      this.htmlToAdd = '<p>Campos Vacios.</p>';
    } else {
      body.titulo = this.titulo
      body.descripcion = this.descripcion
      this.publicationSrv.create_publication(body).subscribe(
        () => {
          this.htmlToAdd = '<p>Publicacion creada con exito.</p>';
        },
        (error) => {
          switch (error.status) {
            case 401:
              this.userSrv.setUserLoggedOut();
              window.location.href = '/';
              break;
            default:
              this.htmlToAdd = '<p>Error en el servidor.</p>';
          }
        }
      )
    }
  }

}
