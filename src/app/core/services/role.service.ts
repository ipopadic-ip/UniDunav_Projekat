import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { inject } from '@angular/core';
import { Role } from '../model/CreateUserRequest.model';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RoleService {
  private apiUrl = 'http://localhost:8080/api/roles';

  private http = inject(HttpClient);


    getAllRoles(): Observable<Role[]> {
        return this.http.get<Role[]>(`${this.apiUrl}`);
    }


    // Sve aktivne role osim STUDENT
    getAssignableRoles(): Observable<Role[]> {
        return this.getAllRoles();
        // return this.getAllRoles().pipe(
        // map(roles => roles.filter(role => role.naziv !== 'STUDENT'))
        // );
    }

    // dohvatanje svih rola sortirano (aktivne prve)
    getAllRolesSorted(): Observable<Role[]> {
    return this.http.get<Role[]>(`${this.apiUrl}/all`);
    }

    deactivateRole(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
    }

    activateRole(id: number): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/${id}/activate`, {});
    }


    updateRole(id: number, updatedRole: Role): Observable<Role> {
    return this.http.put<Role>(`${this.apiUrl}/${id}`, updatedRole);
    }

    createRole(role: Role): Observable<Role> {
        return this.http.post<Role>(`${this.apiUrl}`, role);
    }

    getRoleById(id: number): Observable<Role> {
        return this.http.get<Role>(`${this.apiUrl}/${id}`);
    }


}
