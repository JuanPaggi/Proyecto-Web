import { Component, OnInit } from '@angular/core';
import { TagResponseDto } from 'src/app/dtos/TagResponseDto';
import { TagService } from 'src/app/services/etiquetas/tag.service';
import { UserService } from 'src/app/services/users/user.service';

@Component({
  selector: 'app-panel-etiquetas',
  templateUrl: './panel-etiquetas.component.html',
  styleUrls: ['./panel-etiquetas.component.css', '../panel.component.css']
})
export class PanelEtiquetasComponent implements OnInit {

  htmlToAdd: String;

  tags: TagResponseDto[];

  option: String = "table"

  etiquetaEdit:String
  idEtiquetaEdit:number

  constructor(
    private tagsSrv: TagService,
    private userSrv: UserService
  ) { }

  ngOnInit(): void {
    this.obtenerPublicaciones();
  }

  public obtenerPublicaciones() {
    this.tagsSrv.getAll().subscribe(
      (response) => {
        this.tags = response;
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

  public updatePublication(idEtiqueta: number, etiqueta:String){
    this.etiquetaEdit = etiqueta
    this.idEtiquetaEdit = idEtiqueta
    this.option = 'edit'
  }

  public deleteTag(idEtiqueta: number){
    this.tagsSrv.deleteTag(idEtiqueta).subscribe(
      () => {
        this.tags.forEach((item, index) => {
          if (item.id_etiqueta == idEtiqueta) {
            this.tags.splice(index,1)
          }
        })
        this.htmlToAdd = '<p class="text-danger">Etiqueta eliminada correctamente.</p>';
      },
      (error) => {
        switch (error.status) {
          case 401:
            this.htmlToAdd = '<p class="text-danger">No Autorizado.</p>';
            break;
          case 404:
            this.htmlToAdd = '<p class="text-danger">No existe.</p>';
            break;
          default:
            this.htmlToAdd = '<p class="text-danger">Error en el servidor.</p>';
        }
      }
    )
  }

}
