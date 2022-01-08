import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TagResponseDto } from 'src/app/dtos/TagResponseDto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TagService {

  constructor(
    private http: HttpClient
  ) { }

  private headers = { withCredentials: true };

  public getTag(idEtiqueta: number): Observable<TagResponseDto> {
    return this.http.get<TagResponseDto>(
      environment.apiEndpoint + '/etiquetas/' + idEtiqueta,
      this.headers
    )
  }

  public getAll(): Observable<TagResponseDto[]> {
    return this.http.get<TagResponseDto[]>(
      environment.apiEndpoint + '/etiquetas/all',
      this.headers
    )
  }

}
