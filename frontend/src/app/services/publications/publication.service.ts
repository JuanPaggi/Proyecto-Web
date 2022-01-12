import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PublicationCreateDto } from 'src/app/dtos/PublicationCreateDto';
import { PublicationResponseDto } from 'src/app/dtos/PublicationResponseDto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PublicationService {

  private headers = { withCredentials: true };

  constructor(private http: HttpClient) {
  }

  public get_all_publication(): Observable<PublicationResponseDto[]> {
    return this.http.get<PublicationResponseDto[]>(
      environment.apiEndpoint + '/publicaciones/todas',
      this.headers
    );
  }

  public get_publication(id_publicacion): Observable<PublicationResponseDto> {
    return this.http.get<PublicationResponseDto>(
      environment.apiEndpoint + '/publicaciones/' + id_publicacion,
      this.headers
    );
  }

  public create_publication(body: PublicationCreateDto): Observable<void> {
    return this.http.post<void>(
      environment.apiEndpoint + '/publicaciones',
      body,
      this.headers
    );
  }

  public delete(id_publicacion): Observable<void> {
    return this.http.delete<void>(
      environment.apiEndpoint + '/publicaciones/' + id_publicacion,
      this.headers
    );
  }

}
