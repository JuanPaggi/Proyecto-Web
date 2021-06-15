import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CommentCreateDto } from 'src/app/dtos/CommentCreateDto';
import { CommentResponseDto } from 'src/app/dtos/CommentResponseDto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private headers = { withCredentials: true };

  constructor(private http: HttpClient) {
  }

  public createComment(body: CommentCreateDto): Observable<void> {
    return this.http.post<void>(
      environment.apiEndpoint + '/comentarios',
      body,
      this.headers
    );
  }

  public getComment(id_comentario): Observable<CommentResponseDto> {
    return this.http.get<CommentResponseDto>(
      environment.apiEndpoint + '/comentarios/' + id_comentario
    );
  }

}
