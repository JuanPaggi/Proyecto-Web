import { Component, Input, OnInit } from '@angular/core';
import { PublicationCreateDto } from 'src/app/dtos/PublicationCreateDto';
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

  tagsSelect: TagsValues[]

  tagsResult: number[]

  htmlToAdd: String;

  constructor(
    private publicationSrv: PublicationService,
    private tagSrv: TagService,
    private userSrv: UserService
  ) {
    this.tagsSelect = []
    this.tagsResult = []
   }

  ngOnInit(): void {
    this.getTags()
  }

  public getPublication(){
    this.publicationSrv.get_publication(this.idPublication).subscribe(
      (response)=>{
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
    let body = new PublicationCreateDto()
    if (this.titulo == null || this.descripcion == null) {
      this.htmlToAdd = '<p>Campos Vacios.</p>';
    } else {
      body.titulo = this.titulo
      body.descripcion = this.descripcion
      body.etiquetas = this.generateTags()
      this.publicationSrv.edit_publication(this.idPublication,body).subscribe(
        () => {
          this.htmlToAdd = '<p>Publicacion editada con exito.</p>';
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

  private generateTags() {
    this.tagsResult = []
    this.tagsSelect.forEach(it => {
      if (it.value) {
        this.tagsResult.push(it.id)
      }
    })
    return this.tagsResult
  }

  public getTags() {
    this.tagSrv.getAll().subscribe(
      (response) => {
        response.forEach(it => {
          this.tagsSelect.push(new TagsValues(it.id_etiqueta, false, it.etiqueta))
        })
        this.getPublication()
      }
    )
  }

  public selectTag(id: number, e) {
    this.tagsSelect.forEach(it => {
      if (id == it.id) {
        it.value = e.target.checked
      }
    })
  }

}
