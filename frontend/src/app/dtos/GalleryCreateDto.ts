import { Byte } from "@angular/compiler/src/util";

export class GalleryCreateDto{
    titulo: String;
    descripcion: String;
    etiquetas: number[];
    imagenes: Byte[][];
}