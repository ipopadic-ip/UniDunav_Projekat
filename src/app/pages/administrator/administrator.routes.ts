import { Routes } from '@angular/router';

export const administratorRoutes: Routes = [
  { path: '', loadComponent: () => import('./admin-dashboard/admin-dashboard.component').then(m => m.AdminDashboardComponent) },
  { path: 'add-user', loadComponent: () => import('./user/add-user/add-user.component').then(m => m.AddUserComponent) },
  { path: 'get-user', loadComponent: () => import('./user/user-table/user-table.component').then(m => m.UserTableComponent) },
  { path: 'izmeni-korisnika/:id', loadComponent: () => import('./user/add-user/add-user.component').then(m => m.AddUserComponent) },
  { path: 'uloge', loadComponent: () => import('./role/role-list/role-list.component').then(m => m.RoleListComponent) },
  { path: 'roles/add', loadComponent: () => import('./role/role-form/role-form.component').then(m => m.RoleFormComponent) },
  { path: 'roles/edit/:id', loadComponent: () => import('./role/role-form/role-form.component').then(m => m.RoleFormComponent) },

];
