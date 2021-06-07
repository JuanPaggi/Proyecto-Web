import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TagResponseDto } from 'src/app/dtos/TagResponseDto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EtiquetasService {

  constructor(
    private http: HttpClient
  ) { }

  public getTag(idEtiqueta: number): Observable<TagResponseDto> {
    let headers = {};
    return this.http.get<TagResponseDto>(
      environment.apiEndpoint + 'etiquetas/' + idEtiqueta,
      headers
    )
  }

}
