import { CommentResponseDto } from "./CommentResponseDto";
import { TagResponseDto } from "./TagResponseDto";
import { UserNameResponseDto } from "./UserNameResponseDto";

export class PublicationResponseDto{
    id_publicacion: number;
    descripcion: String;
    fechaCreacion: Date;
    fechaCreacionString: String;
    horarioCreacionString: String;
    titulo: String;
    etiquetas: TagResponseDto[];
    comentarios: CommentResponseDto[];
    usuario: UserNameResponseDto;
}
