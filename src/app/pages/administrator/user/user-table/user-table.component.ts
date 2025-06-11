import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { User } from '../../../../core/model/user.model';
import { UserService } from '../../../../core/services/user.service';
import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-user-table',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.css']
})
export class UserTableComponent {
  private userService = inject(UserService);
  users: User[] = [];

  selectedRole: string | null = null;
  allUsers: User[] = [];
  availableRoles: string[] = [];


  constructor(private router: Router) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
  this.userService.getAllUsers().subscribe({
    next: (data) => {
      const sortedUsers = data.sort((a, b) => {
        // Prvo sortiraj tako da deleted korisnici budu na dnu
        if (a.deleted !== b.deleted) {
          return a.deleted ? 1 : -1; // false (aktivni) idu ispred true (obrisani)
        }

        // Ako su oba deleted ista, sortiraj po imenu + prezimenu
        const imePrezimeA = `${a.ime} ${a.prezime}`.toLowerCase();
        const imePrezimeB = `${b.ime} ${b.prezime}`.toLowerCase();
        return imePrezimeA.localeCompare(imePrezimeB);
      });

      this.users = sortedUsers;
      this.allUsers = sortedUsers;

      // Izdvajanje unikatnih rola
      const roleSet = new Set<string>();
      sortedUsers.forEach(user => {
        user.roles.forEach(role => roleSet.add(role.naziv));
      });
      this.availableRoles = Array.from(roleSet).sort((a, b) => a.localeCompare(b));
    },
    error: (err) => console.error('Greška pri učitavanju korisnika', err)
  });
}



  // loadUsers(): void {
  //   this.userService.getAllUsers().subscribe({
  //   next: (data) => {
  //     this.users = data;
  //     this.allUsers = data;

  //     // Izdvajanje svih unikatnih rola iz korisnika
  //     const roleSet = new Set<string>();
  //     data.forEach(user => {
  //       user.roles.forEach(role => roleSet.add(role.naziv));
  //     });
  //     this.availableRoles = Array.from(roleSet);
  //   },
  //   error: (err) => console.error('Greška pri učitavanju korisnika', err)
  // });
  //   // this.userService.getAllUsers().subscribe({
  //   //   next: (data) => this.users = data,
  //   //   error: (err) => console.error('Greška pri učitavanju korisnika', err)
  //   // });
  // }

  filterByRole(): void {
    if (!this.selectedRole) {
      this.users = this.allUsers;
    } else {
      this.users = this.allUsers.filter(user =>
        user.roles.some(role => role.naziv === this.selectedRole)
      );
    }
  }

  resetFilter(): void {
    this.selectedRole = null;
    this.users = this.allUsers;
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

  eksport(): void {
    this.router.navigate(['/admin/eksport']);
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
