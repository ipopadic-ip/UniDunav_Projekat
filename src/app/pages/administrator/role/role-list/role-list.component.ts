import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Role } from '../../../../core/model/CreateUserRequest.model';
import { RoleService } from '../../../../core/services/role.service';
import { RouterModule, Router } from '@angular/router';

@Component({
  selector: 'app-role-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './role-list.component.html',
})
export class RoleListComponent implements OnInit {
  roles: Role[] = [];

  constructor(private roleService: RoleService, private router: Router) {}

  ngOnInit(): void {
    this.loadRoles();
  }

  loadRoles(): void {
    this.roleService.getAllRolesSorted().subscribe({
      next: (data: Role[]) => this.roles = data,
      error: (err: any) => console.error('Greška pri dohvatanju rola', err)
    });
  }

  activateRole(role: Role): void {
  if (confirm(`Da li želite da aktivirate rolu "${role.naziv}"?`)) {
    this.roleService.activateRole(role.id!).subscribe({
      next: () => this.loadRoles(),
      error: (err: any) => alert('Greška pri aktivaciji role: ' + err.error)
    });
  }
}


  deactivateRole(role: Role): void {
    if (role.naziv === 'ADMIN') {
      alert('ADMIN rola se ne može deaktivirati!');
      return;
    }

    if (confirm(`Da li ste sigurni da želite da deaktivirate rolu "${role.naziv}"?`)) {
      this.roleService.deactivateRole(role.id!).subscribe({
        next: () => this.loadRoles(),
        error: (err: { error: string; }) => alert('Greška pri deaktivaciji role: ' + err.error)
      });
    }
  }

   goBack(): void {
    this.router.navigate(['/admin']);
  }

  editRole(role: Role): void {
    // navigacija na formu za izmenu (pretpostavljamo /roles/edit/:id)
    // možeš otvoriti modal ako želiš inline izmenu
    // ili podeliti formu kao u user add/edit
    this.router.navigate(['/admin/roles/edit', role.id]);
  }
}
