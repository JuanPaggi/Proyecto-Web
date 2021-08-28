export class UsuarioDatosDto {
  id_usuario: number;
  user: String;
  mail: String;
  nombre: String;
  apellido: String;
  mailVerificado: boolean;
  fechaRegistro: Date;
  fechaNacimiento: Date;
  admin: boolean;
}