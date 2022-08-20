import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './component/header/header.component';
import { FooterComponent } from './component/footer/footer.component';
import { EventComponent } from './component/event/event.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './component/login/login.component';
import { PublicationComponent } from './component/publication/publication.component';
import { DatePipe } from '@angular/common';
import { RegisterComponent } from './component/register/register.component';
import { HomeComponent } from './component/home/home.component';
import { PanelComponent } from './component/panel/panel.component';
import { PanelUsuariosComponent } from './component/panel/panel-usuarios/panel-usuarios.component';
import { PanelEtiquetasComponent } from './component/panel/panel-etiquetas/panel-etiquetas.component';
import { PanelGaleriasComponent } from './component/panel/panel-galerias/panel-galerias.component';
import { PanelPublicacionesComponent } from './component/panel/panel-publicaciones/panel-publicaciones.component';
import { GallerysComponent } from './component/gallerys/gallerys.component';
import { PanelPublicacionesCrearComponent } from './component/panel/panel-publicaciones-crear/panel-publicaciones-crear.component';
import { PanelPublicacionesEditarComponent } from './component/panel/panel-publicaciones-editar/panel-publicaciones-editar.component';
import { PanelEtiquetasEditComponent } from './component/panel/panel-etiquetas-edit/panel-etiquetas-edit.component';
import { PanelEtiquetasCrearComponent } from './component/panel/panel-etiquetas-crear/panel-etiquetas-crear.component';
import { PanelGaleriasCrearComponent } from './component/panel/panel-galerias-crear/panel-galerias-crear.component';
import { GalleryImagesComponent } from './component/gallerys/gallery-images/gallery-images.component';
import { PanelGaleriasEditComponent } from './component/panel/panel-galerias-edit/panel-galerias-edit.component';

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {InputTextModule} from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {CardModule} from 'primeng/card';
import {TableModule} from 'primeng/table';
import {InputTextareaModule} from 'primeng/inputtextarea';
import {CheckboxModule} from 'primeng/checkbox';
import {ToggleButtonModule} from 'primeng/togglebutton';
import {MenuModule} from 'primeng/menu';



@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    EventComponent,
    LoginComponent,
    PublicationComponent,
    RegisterComponent,
    HomeComponent,
    PanelComponent,
    PanelPublicacionesComponent,
    PanelUsuariosComponent,
    PanelEtiquetasComponent,
    PanelGaleriasComponent,
    GallerysComponent,
    PanelPublicacionesCrearComponent,
    PanelPublicacionesEditarComponent,
    PanelEtiquetasEditComponent,
    PanelEtiquetasCrearComponent,
    PanelGaleriasCrearComponent,
    GalleryImagesComponent,
    PanelGaleriasEditComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    BrowserAnimationsModule,
    InputTextModule,
    ButtonModule,
    CardModule,
    FormsModule,
    HttpClientModule,
    TableModule,
    InputTextareaModule,
    CheckboxModule,
    ToggleButtonModule,
    MenuModule
    
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
