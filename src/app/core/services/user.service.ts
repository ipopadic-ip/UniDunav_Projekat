import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CreateUserRequest } from '../model/CreateUserRequest.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private apiUrl = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient) {}

  createUser(request: CreateUserRequest): Observable<any> {
    return this.http.post(`${this.apiUrl}/add`, request, {
    responseType: 'text' as const
  });
  
  }
}
