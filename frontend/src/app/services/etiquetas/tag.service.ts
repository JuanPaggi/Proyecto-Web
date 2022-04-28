import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TagResponseDto } from 'src/app/dtos/TagResponseDto';
import { environment } from 'src/environments/environment';
import { TagCreateDto } from 'src/app/dtos/TagCreateDto';

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

  public createTag(body: TagCreateDto): Observable<void> {
    return this.http.post<void>(
      environment.apiEndpoint + '/etiquetas',
      body,
      this.headers
    )
  }

  public editTag(idEtiqueta: number, body: TagCreateDto): Observable<void> {
    return this.http.put<void>(
      environment.apiEndpoint + '/etiquetas/' + idEtiqueta,
      body,
      this.headers
    )
  }

  public deleteTag(idEtiqueta: number): Observable<void> {
    return this.http.delete<void>(
      environment.apiEndpoint + '/etiquetas/' + idEtiqueta,
      this.headers
    )
  }

}
