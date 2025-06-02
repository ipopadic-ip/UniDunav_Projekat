import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { User } from '../../../../core/model/user.model';
import { UserService } from '../../../../core/services/user.service';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-user-table',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.css']
})
export class UserTableComponent {
  private userService = inject(UserService);
  users: User[] = [];

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.userService.getAllUsers().subscribe({
      next: (data) => this.users = data,
      error: (err) => console.error('Greška pri učitavanju korisnika', err)
    });
  }

  onReactivate(userId: number): void {
  this.userService.reactivateUser(userId).subscribe({
    next: () => this.loadUsers(),
    error: (err) => console.error('Greška pri reaktivaciji', err)
  });
}

 goBack(): void {
    this.router.navigate(['/admin']);
  }


  onEdit(userId: number): void {
    // idi na stranicu za izmenu
    // prilagodi rutu po potrebi
    // this.router.navigate([`/izmeni-korisnika/${userId}`]);
    this.router.navigate([`/admin/izmeni-korisnika/${userId}`]);
    // window.location.href = `/admin/izmeni-korisnika/${userId}`;
  }

  onAdd(): void {
  this.router.navigate(['/admin/add-user']);
  }

  onDeactivate(userId: number): void {
    this.userService.deactivateUser(userId).subscribe({
      next: () => this.loadUsers(),
      error: (err) => console.error('Greška pri deaktivaciji', err)
    });
  }
}
