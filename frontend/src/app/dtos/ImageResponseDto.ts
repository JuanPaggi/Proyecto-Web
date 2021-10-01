import { Byte } from "@angular/compiler/src/util";
import { UserNameResponseDto } from "./UserNameResponseDto";

export class ImageResponseDto {
    imagen: Byte[];
    fechaSubida: Date;
    usuario: UserNameResponseDto;
}