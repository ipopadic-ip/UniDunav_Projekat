import { Routes } from '@angular/router';

export const administratorRoutes: Routes = [
  { path: '', loadComponent: () => import('./admin-dashboard/admin-dashboard.component').then(m => m.AdminDashboardComponent) },
  { path: 'add-user', loadComponent: () => import('./add-user/add-user.component').then(m => m.AddUserComponent) },
//   { path: 'fakulteti/:id', loadComponent: () => import('./fakultet-detalji/fakultet-detalji.component').then(m => m.FakultetDetaljiComponent) },
//   { path: 'studijskiprogram/:id', loadComponent: () => import('./studijski-program-detalji/studijski-program-detalji.component').then(m => m.StudijskiProgramDetaljiComponent) },
//   {path: 'departmani/:id', loadComponent: () => import('./departman-detalji/departman-detalji.component').then(m => m.DepartmanDetaljiComponent)},
//   { path: 'katedre/:id', loadComponent: () => import('./katedra-detalji/katedra-detalji.component').then(m => m.KatedraDetaljiComponent)},
//   { path: 'predmeti/:id', loadComponent: () => import('./predmet-detalji/predmet-detalji.component').then(m => m.PredmetDetaljiComponent)}


];
