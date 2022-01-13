import { Component, OnInit } from '@angular/core';
import { TagCreateDto } from 'src/app/dtos/TagCreateDto';
import { TagService } from 'src/app/services/etiquetas/tag.service';
import { UserService } from 'src/app/services/users/user.service';

@Component({
  selector: 'app-panel-etiquetas-crear',
  templateUrl: './panel-etiquetas-crear.component.html',
  styleUrls: ['./panel-etiquetas-crear.component.css', '../panel-etiquetas/panel-etiquetas.component.css']
})
export class PanelEtiquetasCrearComponent implements OnInit {

  htmlToAdd: String;

  etiqueta: String;

  constructor(
    private tagsSrv: TagService,
    private userSrv: UserService
  ) { }

  ngOnInit(): void {
  }

  public createEtiqueta() {
    let body = new TagCreateDto()
    body.etiqueta = this.etiqueta
    this.tagsSrv.createTag(body).subscribe(
      () => {
        this.htmlToAdd = '<p>Etiqueta creada con exito.</p>';
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
