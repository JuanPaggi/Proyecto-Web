import { Component, Input, OnInit } from '@angular/core';
import { PublicationService } from 'src/app/services/publications/publication.service';
import { UserService } from 'src/app/services/users/user.service';

@Component({
  selector: 'app-panel-publicaciones-editar',
  templateUrl: './panel-publicaciones-editar.component.html',
  styleUrls: ['./panel-publicaciones-editar.component.css']
})
export class PanelPublicacionesEditarComponent implements OnInit {

  @Input() idPublication: number;

  titulo: String
  descripcion: String

  htmlToAdd: String;

  constructor(
    private publicationSrv: PublicationService,
    private userSrv: UserService
  ) { }

  ngOnInit(): void {
    this.getPublication()
  }

  public getPublication(){
    this.publicationSrv.get_publication(this.idPublication).subscribe(
      (response)=>{
        this.titulo = response.titulo
        this.descripcion = response.descripcion
      }
    )
  }

  public editPublication(){
    
  }


}
