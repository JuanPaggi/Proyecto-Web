import { Component, OnInit } from '@angular/core';
import { TagResponseDto } from 'src/app/dtos/TagResponseDto';
import { EtiquetasService } from 'src/app/services/etiquetas/etiquetas.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  tag: TagResponseDto = new TagResponseDto();

  constructor(
    private etiquetaService: EtiquetasService
  ) {}

  ngOnInit(): void {
    this.getTag();
  }

  private getTag(){
    this.etiquetaService.getTag(1).subscribe(
      (response) => {
        this.tag = response;
      },
      (error) => {
        switch (error.status){
          case 404:
            console.log("Fallo 404");
            break;
          case 500:
            console.log("Fallo 500");
            break;
          default:
            console.log("NO SE SABE QUE PASO!");
            break;
        }
      }
    )
  }

}
