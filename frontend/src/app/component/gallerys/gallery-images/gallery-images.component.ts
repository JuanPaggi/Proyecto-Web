import { Component, OnInit } from '@angular/core';
import { GalleryResponseDto } from 'src/app/dtos/GalleryResponseDto';
import { GalleryService } from 'src/app/services/gallery/gallery.service';
import { ActivatedRoute } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-gallery-images',
  templateUrl: './gallery-images.component.html',
  styleUrls: ['./gallery-images.component.css']
})
export class GalleryImagesComponent implements OnInit {

  gallery: GalleryResponseDto;

  idGallery: number

  apiURL: string;
  htmlToAdd: String;

  constructor(
    private gallerySrv: GalleryService,
    private route: ActivatedRoute,
  ) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.idGallery = parseInt(params.id_galeria, 10);
    });
    this.apiURL = environment.apiEndpoint;
    this.getGallery()
    this.gallery = new GalleryResponseDto()
  }

  public getGallery() {
    this.gallerySrv.getGallery(this.idGallery).subscribe(
      (response) => {
        this.gallery = response;
      },
      (error) => {
        switch (error.status) {
          case 404:
            this.htmlToAdd = '<p class="text-danger">No existe.</p>';
            break;
          default:
            this.htmlToAdd = '<p class="text-danger">Error en el servidor.</p>';
        }
      }
    );
  }

}
