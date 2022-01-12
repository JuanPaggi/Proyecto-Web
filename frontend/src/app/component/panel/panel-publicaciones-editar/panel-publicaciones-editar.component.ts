import { Component, Input, OnInit } from '@angular/core';
import { PublicationResponseDto } from 'src/app/dtos/PublicationResponseDto';
import { TagResponseDto } from 'src/app/dtos/TagResponseDto';
import { TagService } from 'src/app/services/etiquetas/tag.service';
import { PublicationService } from 'src/app/services/publications/publication.service';
import { UserService } from 'src/app/services/users/user.service';
import { TagsValues } from '../panel-publicaciones-crear/panel-publicaciones-crear.component';

@Component({
  selector: 'app-panel-publicaciones-editar',
  templateUrl: './panel-publicaciones-editar.component.html',
  styleUrls: ['./panel-publicaciones-editar.component.css']
})
export class PanelPublicacionesEditarComponent implements OnInit {

  @Input() idPublication: number;

  titulo:String
  descripcion:String
  tags: TagResponseDto[]

  tagsSelect: TagsValues[]

  htmlToAdd: String;

  constructor(
    private publicationSrv: PublicationService,
    private tagSrv: TagService,
    private userSrv: UserService
  ) {
    this.tagsSelect = []
   }

  ngOnInit(): void {
    this.getTags()
  }

  public getPublication(){
    this.publicationSrv.get_publication(this.idPublication).subscribe(
      (response)=>{
        console.log(response)
        console.log(this.tagsSelect)
        this.titulo = response.titulo
        this.descripcion = response.descripcion
        this.tagsSelect.forEach(it=>{
          response.etiquetas.forEach(it2=>{
            if (it2.id_etiqueta == it.id) {
              it.value = true
            }
          })
        })
      }
    )
  }

  public editPublication(){
    
  }

  public getTags() {
    this.tagSrv.getAll().subscribe(
      (response) => {
        response.forEach(it => {
          this.tagsSelect.push(new TagsValues(it.id_etiqueta, false))
        })
        this.tags = response
        this.getPublication()
      }
    )
  }


}
