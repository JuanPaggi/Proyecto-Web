import { Component, Input, OnInit } from '@angular/core';
import { TagCreateDto } from 'src/app/dtos/TagCreateDto';
import { TagService } from 'src/app/services/etiquetas/tag.service';
import { UserService } from 'src/app/services/users/user.service';

@Component({
  selector: 'app-panel-etiquetas-edit',
  templateUrl: './panel-etiquetas-edit.component.html',
  styleUrls: ['./panel-etiquetas-edit.component.css', '../panel-etiquetas/panel-etiquetas.component.css']
})
export class PanelEtiquetasEditComponent implements OnInit {

  htmlToAdd: String;

  @Input() idEtiqueta: number;
  @Input() etiqueta: String;

  constructor(
    private tagSrv: TagService,
    private userSrv: UserService
  ) { }

  ngOnInit(): void {

  }

  public editEtiqueta() {
    let body = new TagCreateDto()
    body.etiqueta = this.etiqueta
    console.log(this.idEtiqueta, body);
    
    this.tagSrv.editTag(this.idEtiqueta, body).subscribe(
      () => {
        this.htmlToAdd = '<p>Etiqueta editada con exito.</p>';
      }, (error) => {
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
