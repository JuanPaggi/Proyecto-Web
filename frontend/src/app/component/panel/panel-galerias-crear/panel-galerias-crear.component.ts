import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { GalleryCreateDto } from 'src/app/dtos/GalleryCreateDto';
import { TagResponseDto } from 'src/app/dtos/TagResponseDto';
import { TagService } from 'src/app/services/etiquetas/tag.service';
import { GalleryService } from 'src/app/services/gallery/gallery.service';
import { UserService } from 'src/app/services/users/user.service';
import { TagsValues } from '../panel-publicaciones-crear/panel-publicaciones-crear.component';

@Component({
  selector: 'app-panel-galerias-crear',
  templateUrl: './panel-galerias-crear.component.html',
  styleUrls: ['./panel-galerias-crear.component.css']
})
export class PanelGaleriasCrearComponent implements OnInit {

  @ViewChild('imageUpload', { static: false }) imagInput: ElementRef;

  imageFileNoticia: number[][];
  imagenesUrlNoticia: String[];

  titulo: String
  descripcion: String

  tags: TagResponseDto[]

  tagsResult: number[]

  tagsSelect: TagsValues[]

  htmlToAdd: String;

  constructor(
    private gallerySrv: GalleryService,
    private tagSrv: TagService,
    private userSrv: UserService
  ) {
    this.tagsSelect = []
    this.tagsResult = []
    this.imageFileNoticia = [];
    this.imagenesUrlNoticia = [];
  }

  ngOnInit(): void {
    this.getTags()
  }

  openImage() {
    this.imagInput.nativeElement.click();
    this.imagInput.nativeElement.onchange = () => {
      const fr = new FileReader();
      let firstExecution = true;
      fr.onload = () => {
        if(firstExecution) {
          const arrayBuffer = fr.result as ArrayBuffer;
          this.imageFileNoticia.push(Array.from(new Uint8Array(arrayBuffer)));
          firstExecution = false;
          console.log('Imagen cargada');
          fr.readAsDataURL(this.imagInput.nativeElement.files[0]);
        } else {
          this.imagenesUrlNoticia.push(fr.result as string);
          console.log(this.imagenesUrlNoticia);
        }
      
      };
      fr.readAsArrayBuffer(this.imagInput.nativeElement.files[0]);
    };
  }
  
  deleteImage(idx: number) {
    this.imageFileNoticia.splice(idx,1);
    this.imagenesUrlNoticia.splice(idx,1);
  }

  public createGallery() {
    let body = new GalleryCreateDto()
    if (this.titulo == null || this.descripcion == null) {
      this.htmlToAdd = '<p>Campos Vacios.</p>';
    } else {
      body.titulo = this.titulo
      body.descripcion = this.descripcion
      body.etiquetas = this.generateTags()
      body.imagenes = this.imageFileNoticia;
      this.gallerySrv.create(body).subscribe(
        () => {
          this.htmlToAdd = '<p>Galeria creada con exito.</p>';
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
    this.tagsSelect.forEach(it => {
      if (id == it.id) {
        it.value = e.target.checked
      }
    })
  }

}
