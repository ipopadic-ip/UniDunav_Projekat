import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../../../core/services/user.service';
import { CreateUserRequest, Role } from '../../../../core/model/CreateUserRequest.model';
import { RoleService } from '../../../../core/services/role.service';
import { User } from '../../../../core/model/user.model';
import { ActivatedRoute, Router } from '@angular/router'; // ✅ Ispravljen import Router-a

@Component({
  selector: 'app-add-user',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl:'./add-user.component.html',
  styleUrl:'./add-user.component.css'
})
export class AddUserComponent implements OnInit {
  user: CreateUserRequest = {
    email: '',
    password: '',
    ime: '',
    prezime: '',
    adresa: '',
    jmbg: '',
    roles: []
  };

  isEditMode = false;
  userId?: number;

  availableRoles: Role[] = [];

  constructor(
    private userService: UserService,
    private roleService: RoleService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadRoles(); // obavezno pre svega
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.isEditMode = true;
        this.userId = +id;
        this.loadUser(+id);
      }
    });
  }

  goBack(): void {
    this.router.navigate(['/admin/get-user']);
  }

  loadRoles(): void {
    this.roleService.getAllRoles().subscribe({
      next: (roles: Role[]) => {
        this.availableRoles = roles;
      },
      error: (err) => {
        console.error('Greška pri učitavanju rola:', err);
        alert('Nije moguće učitati role.');
      }
    });
  }

  loadUser(id: number): void {
    this.userService.getUserById(id).subscribe({
      next: (data) => {
        this.user = {
          ...data,
          id: data.id,
          // roles: data.roles.map((r: Role) => ({ naziv: r.naziv })),
          roles: data.roles.map((r: Role) => ({ id: r.id, naziv: r.naziv })),
          password: ''
        };
      },
      error: (err) => {
        console.error(err);
        alert('Greška pri učitavanju korisnika.');
      }
    });
  }

  toggleRole(event: any): void {
    const roleName = event.target.value;
    const isChecked = event.target.checked;

    const selectedRole = this.availableRoles.find(r => r.naziv === roleName);
    if (!selectedRole) return;

    if (isChecked && !this.hasRole(roleName)) {
      this.user.roles.push({ id: selectedRole.id, naziv: selectedRole.naziv });
    } else {
      this.user.roles = this.user.roles.filter(r => r.naziv !== roleName);
    }

    // if (isChecked && !this.hasRole(roleName)) {
    //   this.user.roles.push({ naziv: roleName });
    // }
    // else {
    //   this.user.roles = this.user.roles.filter(r => r.naziv !== roleName);
    // }
  }

  hasRole(roleName: string): boolean {
    return this.user.roles.some(r => r.naziv === roleName);
  }

  submit(): void {
    if (this.isEditMode && this.userId !== undefined) {
      this.userService.updateUser(this.userId, this.user).subscribe({
        next: () => {
          alert('Korisnik uspešno ažuriran!');
        },
        error: (err) => {
          console.error(err);
          alert('Greška pri ažuriranju korisnika.');
        }
      });
    } else {
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
}

// export class AddUserComponent {
//   user: CreateUserRequest = {
//     email: '',
//     password: '',
//     ime: '',
//     prezime: '',
//     adresa: '',
//     jmbg: '',
//     roles: []
//   };

//   availableRoles: string[] = ['ADMIN', 'SLUZBENIK', 'PROFESOR'];

//   constructor(private userService: UserService) {}

//   toggleRole(event: any): void {
//     const roleName = event.target.value;
//     const isChecked = event.target.checked;

//     if (isChecked) {
//       this.user.roles.push({ naziv: roleName });
//     } else {
//       this.user.roles = this.user.roles.filter(r => r.naziv !== roleName);
//     }
//   }

//   submit(): void {
//     this.userService.createUser(this.user).subscribe({
//       next: () => {
//         alert('Korisnik uspešno dodat!');
//         this.user = {
//           email: '',
//           password: '',
//           ime: '',
//           prezime: '',
//           adresa: '',
//           jmbg: '',
//           roles: []
//         };
//       },
//       error: (err) => {
//         console.error(err);
//         alert('Greška prilikom dodavanja korisnika.');
//       }
//     });
//   }
// }

