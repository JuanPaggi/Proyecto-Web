import { Component, OnInit } from '@angular/core';
import { PublicationResponseDto } from 'src/app/dtos/PublicationResponseDto';
import { PublicationService } from 'src/app/services/publications/publication.service';

@Component({
  selector: 'app-panel-publicaciones',
  templateUrl: './panel-publicaciones.component.html',
  styleUrls: ['./panel-publicaciones.component.css']
})
export class PanelPublicacionesComponent implements OnInit {

  publicaciones: PublicationResponseDto[];

  constructor(
    private publicationSrv: PublicationService,
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
        console.log("Error al obtener las publicaciones.");
      }
    );
  }

}
