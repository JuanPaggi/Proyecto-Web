import { Component, OnInit } from '@angular/core';
import { PublicationCreateDto } from 'src/app/dtos/PublicationCreateDto';
import { TagResponseDto } from 'src/app/dtos/TagResponseDto';
import { TagService } from 'src/app/services/etiquetas/tag.service';
import { PublicationService } from 'src/app/services/publications/publication.service';
import { UserService } from 'src/app/services/users/user.service';

@Component({
  selector: 'app-panel-publicaciones-crear',
  templateUrl: './panel-publicaciones-crear.component.html',
  styleUrls: ['./panel-publicaciones-crear.component.css']
})
export class PanelPublicacionesCrearComponent implements OnInit {

  titulo: String
  descripcion: String

  
  tags: TagResponseDto[]
  
  tagsResult: number[]

  tagsSelect: TagsValues[]

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

  public createPublication() {
    let body = new PublicationCreateDto()
    if (this.titulo == null || this.descripcion == null) {
      this.htmlToAdd = '<p>Campos Vacios.</p>';
    } else {
      body.titulo = this.titulo
      body.descripcion = this.descripcion
      body.etiquetas = this.generateTags()
      this.publicationSrv.create_publication(body).subscribe(
        () => {
          this.htmlToAdd = '<p>Publicacion creada con exito.</p>';
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
    console.log(this.tagsSelect)
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
        console.log(response)
        response.forEach(it => {
          this.tagsSelect.push(new TagsValues(it.id_etiqueta, false, it.etiqueta))
        })
        this.tags = response
      }
    )
  }

  public selectTag(id: number, e) {
    console.log(e);
    
    this.tagsSelect.forEach(it => {
      if (id == it.id) {
        it.value = e.target.checked
      }
    })
  }

}

export class TagsValues {

  id: number
  value: boolean
  etiqueta: String

  constructor(id: number, value: boolean, etiqueta: String) {
    this.id = id
    this.value = value
    this.etiqueta = etiqueta
  }

}