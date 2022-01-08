import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GalleryResponseDto } from 'src/app/dtos/GalleryResponseDto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GalleryService {

  constructor(private http: HttpClient) {
  }

  private headers = { withCredentials: true };

  public  getGallery(idGaleria: number): Observable <GalleryResponseDto> {
    return this.http.get<GalleryResponseDto>(
      environment.apiEndpoint + '/galerias/' + idGaleria,
      this.headers
    )
  }

  public  getAll(): Observable <GalleryResponseDto[]> {
    return this.http.get<GalleryResponseDto[]>(
      environment.apiEndpoint + '/galerias/all',
      this.headers
    )
  }

}
