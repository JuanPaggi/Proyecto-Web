# Proyecto-Web

Proyecto Web con Backend y Frontend

## Frontend:

### Intalar angular:

- npm install -g @angular/cli

### Actualizamos las dependencias:
- npm install

#### Crear Proyecto
- ng new nombre

#### Crear componente
- ng generate component nombre

#### Crear servicio
- ng generate service nombre

#### Levantar proyecto
- ng serve -o

Para levantar el proyecto en red
	1) Colocar la url con ip publica en origin del properties del backend.
	2) Cambiar la url del backend en enviroments en el frontend.
	3) ng serve --disable-host-check --host "ipLocal"
	Se levanta en: ipPublica:4200

#### Compilar Proyecto
- ng build