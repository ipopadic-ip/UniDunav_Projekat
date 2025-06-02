import { Component, OnInit } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Role } from '../../../../core/model/CreateUserRequest.model';
import { RoleService } from '../../../../core/services/role.service';

@Component({
  selector: 'app-role-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './role-form.component.html',
})
export class RoleFormComponent implements OnInit {
  roleForm!: FormGroup;
  editing: boolean = false;
  roleId!: number;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private roleService: RoleService
  ) {}

  ngOnInit(): void {
    this.roleForm = this.fb.group({
      naziv: ['', Validators.required]
    });

    this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.editing = true;
        this.roleId = +id;
        this.loadRole(this.roleId);
      }
    });
  }

   goBack(): void {
    this.router.navigate(['/admin/uloge']);
  }

  loadRole(id: number): void {
    this.roleService.getRoleById(id).subscribe({
      next: (role: Role) => {
        this.roleForm.patchValue({
          naziv: role.naziv
        });
      },
      error: err => {
        alert('Greška pri učitavanju role.');
        this.router.navigate(['/admin']);
      }
    });
  }

  submit(): void {
    if (this.roleForm.invalid) return;

    const roleData: Role = {
      naziv: this.roleForm.value.naziv
    };

    if (this.editing) {
      this.roleService.updateRole(this.roleId, roleData).subscribe({
        next: () => this.router.navigate(['/admin/uloge']),
        error: err => alert('Greška pri izmeni role.')
      });
    } else {
      this.roleService.createRole(roleData).subscribe({
        next: () => this.router.navigate(['/admin/uloge']),
        error: err => alert('Greška pri dodavanju role.')
      });
    }
  }

  cancel(): void {
    this.router.navigate(['/admin/uloge']);
  }
}
