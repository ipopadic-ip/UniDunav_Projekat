import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Profesor } from '../model/profesor.model';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ProfesorService {
  constructor(private http: HttpClient) {}

  getAllProfesori(): Observable<Profesor[]> {
    return this.http.get<Profesor[]>('http://localhost:8080/api/profesor');
  }
}
