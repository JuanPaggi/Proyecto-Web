import { Component, Input, OnInit } from '@angular/core';
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

  opcion: String = "table"

  idPublicacionEdit:number

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

  public deletePublication(idPublicacion: number){
    this.publicationSrv.delete(idPublicacion).subscribe(
      () => {
        this.publicaciones.forEach((item, index) => {
          if (item.id_publicacion == idPublicacion) {
            this.publicaciones.splice(index,1)
          }
        })
        this.htmlToAdd = '<p class="text-danger">Publicacion eliminada correctamente.</p>';
      },
      (error) => {
        switch (error.status) {
          case 404:
            this.htmlToAdd = '<p class="text-danger">No existe.</p>';
            break;
          default:
            this.htmlToAdd = '<p class="text-danger">Error en el servidor.</p>';
        }
      }
    )
  }

  public updatePublication(idPublicacion: number){
    this.idPublicacionEdit = idPublicacion
    this.opcion = 'edit'
  }

}
