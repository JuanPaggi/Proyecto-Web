import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EventComponent } from './component/event/event.component';
import { GalleryImagesComponent } from './component/gallerys/gallery-images/gallery-images.component';
import { GallerysComponent } from './component/gallerys/gallerys.component';
import { HomeComponent } from './component/home/home.component';
import { LoginComponent } from './component/login/login.component';
import { PanelComponent } from './component/panel/panel.component';
import { RegisterComponent } from './component/register/register.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
  },
  {
    path: 'panel',
    component: PanelComponent,
  },
  {
    path: 'event',
    component: EventComponent,
  },
  {
    path: 'gallerys',
    component: GallerysComponent,
  },
  {
    path: 'gallerys/:id_galeria',
    component: GalleryImagesComponent
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'registro',
    component: RegisterComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
