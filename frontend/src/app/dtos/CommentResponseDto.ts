import { UserNameResponseDto } from "./UserNameResponseDto";

export class CommentResponseDto{
    id_comentario: number;
    texto: String;
    fechaCreacion: Date;
    fechaCreacionString: String;
    horarioCreacionString: String;
    user: UserNameResponseDto;
}