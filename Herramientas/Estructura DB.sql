
-- ---
-- Table 'usuarios'
-- 
-- ---

DROP TABLE IF EXISTS `usuarios`;

CREATE TABLE `usuarios` (
  `id_usuario` INTEGER NOT NULL AUTO_INCREMENT,
  `user` VARCHAR(30) NOT NULL UNIQUE,
  `clave` VARCHAR(50) NOT NULL,
  `mail` VARCHAR(100) NOT NULL UNIQUE,
  `nombre` VARCHAR(100) NOT NULL,
  `apellido` VARCHAR(100) NOT NULL,
  `mail_verificado` bit NOT NULL,
  `fecha_registro` DATETIME NOT NULL,
  `fecha_nacimiento` DATETIME NOT NULL,
  `codigo_verificacion` VARCHAR(6) NOT NULL,
  `admin` bit NOT NULL,
  `id_imagen` INTEGER DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
KEY (`id_imagen`)
);

-- ---
-- Table 'imagenes'
-- 
-- ---

DROP TABLE IF EXISTS `imagenes`;

CREATE TABLE `imagenes` (
  `id_imagen` INTEGER NOT NULL AUTO_INCREMENT,
  `imagen` MEDIUMBLOB NOT NULL,
  `imagen_hash` BINARY(20) NOT NULL,
  `fecha_subida` DATETIME NOT NULL,
  `id_usuario` INTEGER NULL,
  PRIMARY KEY (`id_imagen`),
KEY (`id_usuario`)
);

-- ---
-- Table 'galerias'
-- 
-- ---

DROP TABLE IF EXISTS `galerias`;

CREATE TABLE `galerias` (
  `id_galeria` INTEGER AUTO_INCREMENT NOT NULL,
  `titulo` VARCHAR(200) NOT NULL UNIQUE,
  `descripcion` VARCHAR(10000) NOT NULL,
  `fecha_creacion` DATETIME NOT NULL,
  `id_usuario` INTEGER NULL,
  PRIMARY KEY (`id_galeria`),
KEY (`id_usuario`)
);

-- ---
-- Table 'imagenes_galerias'
-- 
-- ---

DROP TABLE IF EXISTS `imagenes_galerias`;

CREATE TABLE `imagenes_galerias` (
  `id_galeria` INTEGER NOT NULL,
  `id_imagen` INTEGER NOT NULL,
  PRIMARY KEY (`id_imagen`, `id_galeria`)
);

-- ---
-- Table 'etiquetas'
-- 
-- ---

DROP TABLE IF EXISTS `etiquetas`;

CREATE TABLE `etiquetas` (
  `id_etiqueta` INTEGER AUTO_INCREMENT NOT NULL,
  `etiqueta` VARCHAR(100) NOT NULL UNIQUE,
  PRIMARY KEY (`id_etiqueta`)
);

-- ---
-- Table 'galerias_etiquetas'
-- 
-- ---

DROP TABLE IF EXISTS `galerias_etiquetas`;

CREATE TABLE `galerias_etiquetas` (
  `id_galeria` INTEGER NOT NULL,
  `id_etiqueta` INTEGER NOT NULL,
  PRIMARY KEY (`id_galeria`, `id_etiqueta`)
);

-- ---
-- Table 'publicaciones'
-- 
-- ---

DROP TABLE IF EXISTS `publicaciones`;

CREATE TABLE `publicaciones` (
  `id_publicacion` INTEGER NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(200) NOT NULL,
  `descripcion` VARCHAR(10000) NOT NULL,
  `fecha_creacion` DATETIME NOT NULL,
  `id_usuario` INTEGER NOT NULL,
  PRIMARY KEY (`id_publicacion`),
KEY (`id_usuario`)
);

-- ---
-- Table 'imagenes_publicaciones'
-- 
-- ---

DROP TABLE IF EXISTS `imagenes_publicaciones`;

CREATE TABLE `imagenes_publicaciones` (
  `id_imagen` INTEGER NOT NULL,
  `id_publicacion` INTEGER NOT NULL,
  PRIMARY KEY (`id_imagen`, `id_publicacion`)
);

-- ---
-- Table 'publicaciones_etiquetas'
-- 
-- ---

DROP TABLE IF EXISTS `publicaciones_etiquetas`;

CREATE TABLE `publicaciones_etiquetas` (
  `id_etiqueta` INTEGER NOT NULL,
  `id_publicacion` INTEGER NOT NULL,
  PRIMARY KEY (`id_etiqueta`, `id_publicacion`)
);

-- ---
-- Table 'comentarios'
-- 
-- ---

DROP TABLE IF EXISTS `comentarios`;

CREATE TABLE `comentarios` (
  `id_comentario` INTEGER NOT NULL AUTO_INCREMENT,
  `id_usuario` INTEGER NOT NULL,
  `fecha_creacion` DATETIME NOT NULL,
  `texto` VARCHAR(5000) NOT NULL,
  `id_publicacion` INTEGER NOT NULL,
  PRIMARY KEY (`id_comentario`),
KEY (`id_usuario`, `id_publicacion`)
);

-- ---
-- Foreign Keys
-- ---

ALTER TABLE `usuarios` ADD FOREIGN KEY (id_imagen) REFERENCES `imagenes` (`id_imagen`);
ALTER TABLE `imagenes` ADD FOREIGN KEY (id_usuario) REFERENCES `usuarios` (`id_usuario`);
ALTER TABLE `galerias` ADD FOREIGN KEY (id_usuario) REFERENCES `usuarios` (`id_usuario`);
ALTER TABLE `imagenes_galerias` ADD FOREIGN KEY (id_galeria) REFERENCES `galerias` (`id_galeria`);
ALTER TABLE `imagenes_galerias` ADD FOREIGN KEY (id_imagen) REFERENCES `imagenes` (`id_imagen`);
ALTER TABLE `galerias_etiquetas` ADD FOREIGN KEY (id_galeria) REFERENCES `galerias` (`id_galeria`);
ALTER TABLE `galerias_etiquetas` ADD FOREIGN KEY (id_etiqueta) REFERENCES `etiquetas` (`id_etiqueta`);
ALTER TABLE `publicaciones` ADD FOREIGN KEY (id_usuario) REFERENCES `usuarios` (`id_usuario`);
ALTER TABLE `imagenes_publicaciones` ADD FOREIGN KEY (id_imagen) REFERENCES `imagenes` (`id_imagen`);
ALTER TABLE `imagenes_publicaciones` ADD FOREIGN KEY (id_publicacion) REFERENCES `publicaciones` (`id_publicacion`);
ALTER TABLE `publicaciones_etiquetas` ADD FOREIGN KEY (id_etiqueta) REFERENCES `etiquetas` (`id_etiqueta`);
ALTER TABLE `publicaciones_etiquetas` ADD FOREIGN KEY (id_publicacion) REFERENCES `publicaciones` (`id_publicacion`);
ALTER TABLE `comentarios` ADD FOREIGN KEY (id_usuario) REFERENCES `usuarios` (`id_usuario`);
ALTER TABLE `comentarios` ADD FOREIGN KEY (id_publicacion) REFERENCES `publicaciones` (`id_publicacion`);
