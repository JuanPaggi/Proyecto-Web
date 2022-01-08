import { Component, OnInit } from '@angular/core';
import { GalleryResponseDto } from 'src/app/dtos/GalleryResponseDto';
import { GalleryService } from 'src/app/services/gallery/gallery.service';
import { UserService } from 'src/app/services/users/user.service';

@Component({
  selector: 'app-panel-galerias',
  templateUrl: './panel-galerias.component.html',
  styleUrls: ['./panel-galerias.component.css', '../panel.component.css']
})
export class PanelGaleriasComponent implements OnInit {

  htmlToAdd: String;

  gallerys: GalleryResponseDto[];

  constructor(
    private gallerySrv: GalleryService,
    private userSrv: UserService
  ) { }

  ngOnInit(): void {
    this.getGallerys();
  }

  public getGallerys() {
    this.gallerySrv.getAll().subscribe(
      (response) => {
        this.gallerys = response;
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
