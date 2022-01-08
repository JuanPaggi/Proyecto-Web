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
}
