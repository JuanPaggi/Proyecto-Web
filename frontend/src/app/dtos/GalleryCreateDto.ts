import { ImageResponseDto } from "./ImageResponseDto";
import { TagResponseDto } from "./TagResponseDto";

export class GalleryCreateDto{
    titulo: String;
    descripcion: String;
    etiquetas: TagResponseDto[];
    imagenes: ImageResponseDto[];
}