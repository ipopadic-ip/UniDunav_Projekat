import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../../core/services/user.service';
import { CreateUserRequest, Role } from '../../../core/model/CreateUserRequest.model';

@Component({
  selector: 'app-add-user',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl:'./add-user.component.html',
  styleUrl:'./add-user.component.css'
})
export class AddUserComponent {
  user: CreateUserRequest = {
    email: '',
    password: '',
    ime: '',
    prezime: '',
    adresa: '',
    jmbg: '',
    roles: []
  };

  availableRoles: string[] = ['ADMIN', 'SLUZBENIK', 'PROFESOR'];

  constructor(private userService: UserService) {}

  toggleRole(event: any): void {
    const roleName = event.target.value;
    const isChecked = event.target.checked;

    if (isChecked) {
      this.user.roles.push({ naziv: roleName });
    } else {
      this.user.roles = this.user.roles.filter(r => r.naziv !== roleName);
    }
  }

  submit(): void {
    this.userService.createUser(this.user).subscribe({
      next: () => {
        alert('Korisnik uspešno dodat!');
        this.user = {
          email: '',
          password: '',
          ime: '',
          prezime: '',
          adresa: '',
          jmbg: '',
          roles: []
        };
      },
      error: (err) => {
        console.error(err);
        alert('Greška prilikom dodavanja korisnika.');
      }
    });
  }
}

