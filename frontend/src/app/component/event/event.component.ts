import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { CommentCreateDto } from 'src/app/dtos/CommentCreateDto';
import { PublicationResponseDto } from 'src/app/dtos/PublicationResponseDto';
import { User } from 'src/app/dtos/User.model';
import { CommentService } from 'src/app/services/comments/comment.service';
import { PublicationService } from 'src/app/services/publications/publication.service';
import { UserService } from 'src/app/services/users/user.service';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit {

  publicaciones: PublicationResponseDto[];

  comentarioInput: String;

  user: User;

  htmlToAdd: String;

  constructor(
    private publicationSrv: PublicationService,
    private commentSrv: CommentService,
    private datepipe: DatePipe,
    private usuariosSrv: UserService,
  ) {
    this.publicaciones = [];
  }

  ngOnInit(): void {
    this.user = this.usuariosSrv.getUserLoggedIn();
    this.obtenerPublicaciones();
  }

  public obtenerPublicaciones() {
    this.publicationSrv.get_all_publication().subscribe(
      (response) => {
        console.log(response);
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
      (error) => {
        console.log("Error al obtener las publicaciones.");
      }
    );
  }

  public cargarComentario(id_publicacion: number) {
    let comentario = new CommentCreateDto();
    comentario.texto = this.comentarioInput;
    comentario.id_publicacion = id_publicacion;
    this.commentSrv.createComment(comentario).subscribe(
      (response) => {
        this.commentSrv.getComment(response).subscribe(
          (response) => {
            response.fechaCreacionString = this.datepipe.transform(response.fechaCreacion, 'dd-MM-yyyy');
            response.horarioCreacionString = this.datepipe.transform(response.fechaCreacion, 'HH:mm:ss');
            for (let index = 0; index < this.publicaciones.length; index++) {
              if(this.publicaciones[index].id_publicacion === id_publicacion){
                this.publicaciones[index].comentarios.push(response);
              }
            }
          },
          (error) => {
            switch (error.status) {
              case 404:
                this.htmlToAdd = '<p class="text-danger">No existe la publicacion.</p>';
                break;
              default:
                this.htmlToAdd = '<p class="text-danger">Error en el servidor.</p>';
            }
          }
        );
      },
      (error) => {
        switch (error.status) {
          case 401:
            this.usuariosSrv.setUserLoggedOut();
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

  public borrarComentario(indice_publicacion:number, id_comentario: number) {
    this.commentSrv.deleteComment(id_comentario).subscribe(
      (response)=>{
        for (let index = 0; index < this.publicaciones[indice_publicacion].comentarios.length; index++) {
          if (this.publicaciones[indice_publicacion].comentarios[index].id_comentario == id_comentario) {
            this.publicaciones[indice_publicacion].comentarios.splice(index,1);
          }
        }
      },
      (error)=>{
        switch (error.status) {
          case 401:
            this.usuariosSrv.setUserLoggedOut();
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
