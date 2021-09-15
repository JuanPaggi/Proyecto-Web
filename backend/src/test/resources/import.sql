INSERT INTO usuarios (id_usuario, user, clave, mail, nombre, apellido, mail_verificado, fecha_registro, fecha_nacimiento, codigo_verificacion, admin, id_imagen, clave_temporal) VALUES (1, 'juanpaggi', 'juanpaggi', 'juanpaggi@hotmail.com', 'juan', 'paggi', 1, '2021-05-31 21:13:45', '1996-01-25 21:00:00', '741271', 1, null, null);

INSERT INTO publicaciones (id_publicacion, titulo, descripcion, fecha_creacion, id_usuario) VALUES (1, 'La Mona Lisa', 'El Retrato de Lisa Gherardini, esposa de Francesco del Giocondo,1​ más conocido como La Gioconda (La Joconde en francés) o La Mona Lisa, es una obra pictórica del polímata renacentista italiano Leonardo da Vinci. Fue adquirida por el rey Francisco I de Francia a comienzos del siglo XVI y desde entonces es propiedad del Estado francés. Se halla expuesta en el Museo del Louvre de París, siendo, sin duda, la «joya» de sus colecciones.', '2021-06-10 23:44:18', 1);

INSERT INTO etiquetas (id_etiqueta,etiqueta) VALUES (1,'hola');

INSERT INTO publicaciones_etiquetas (id_etiqueta,id_publicacion) VALUES (1,1);
