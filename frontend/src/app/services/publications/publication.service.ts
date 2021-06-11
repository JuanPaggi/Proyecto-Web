import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
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

}
