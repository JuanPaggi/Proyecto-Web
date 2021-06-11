import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { PublicationResponseDto } from 'src/app/dtos/PublicationResponseDto';
import { PublicationService } from 'src/app/services/publications/publication.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  publicaciones: PublicationResponseDto[];

  constructor(
    private publicationSrv: PublicationService,
    private datepipe: DatePipe,
  ) {
    this.publicaciones = [];
   }

  ngOnInit(): void {
    this.obtenerPublicaciones();
  }

  public obtenerPublicaciones(){
    this.publicationSrv.get_all_publication().subscribe(
      (response) =>{
        for (let index = 0; index < response.length; index++) {
          response[index].fechaCreacionString = this.datepipe.transform(response[index].fechaCreacion, 'dd-MM-yyyy');
          response[index].horarioCreacionString = this.datepipe.transform(response[index].fechaCreacion, 'HH:mm:ss');
          for (let index2 = 0; index2 < response[index].comentarios.length; index2++) {
            response[index].comentarios[index2].fechaCreacionString = this.datepipe.transform(response[index].comentarios[index2].fechaCreacion, 'dd-MM-yyyy');
            response[index].comentarios[index2].horarioCreacionString = this.datepipe.transform(response[index].comentarios[index2].fechaCreacion, 'HH:mm:ss');
          }
        }
        this.publicaciones = response;
      },
      (error) =>{
        console.log("Error al obtener las publicaciones.");
      }
    );
  }

}
