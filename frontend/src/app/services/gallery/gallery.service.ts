import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GalleryCreateDto } from 'src/app/dtos/GalleryCreateDto';
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

  public create(body: GalleryCreateDto): Observable<void> {
    return this.http.post<void>(
      environment.apiEndpoint + '/galerias',
      body,
      this.headers
    );
  }

  public delete(id_gallery: number): Observable<void> {
    return this.http.delete<void>(
      environment.apiEndpoint + '/galerias/' + id_gallery,
      this.headers
    );
  }

}
