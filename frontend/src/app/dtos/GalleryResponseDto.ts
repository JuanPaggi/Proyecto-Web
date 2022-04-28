import { TagResponseDto } from "./TagResponseDto";
import { UserNameResponseDto } from "./UserNameResponseDto";

export class GalleryResponseDto{
    id_galeria: number;
    titulo: String;
    descripcion: String;
    fechaCreacion: Date;
    etiquetas: TagResponseDto[];
    usuario: UserNameResponseDto;
    imagenes: String[];
}