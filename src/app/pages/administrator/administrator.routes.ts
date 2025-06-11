import { Routes } from '@angular/router';

export const administratorRoutes: Routes = [
  { path: '', loadComponent: () => import('./admin-dashboard/admin-dashboard.component').then(m => m.AdminDashboardComponent) },
  { path: 'add-user', loadComponent: () => import('./user/add-user/add-user.component').then(m => m.AddUserComponent) },
  { path: 'get-user', loadComponent: () => import('./user/user-table/user-table.component').then(m => m.UserTableComponent) },
  { path: 'izmeni-korisnika/:id', loadComponent: () => import('./user/add-user/add-user.component').then(m => m.AddUserComponent) },
  { path: 'uloge', loadComponent: () => import('./role/role-list/role-list.component').then(m => m.RoleListComponent) },
  { path: 'roles/add', loadComponent: () => import('./role/role-form/role-form.component').then(m => m.RoleFormComponent) },
  { path: 'roles/edit/:id', loadComponent: () => import('./role/role-form/role-form.component').then(m => m.RoleFormComponent) },
  { path: 'univerzitet', loadComponent: () => import('./univerzitet/univerzitet-form/univerzitet-form.component').then(m => m.UniverzitetFormComponent) },
  { path: 'fakultet', loadComponent: () => import('./fakultet/fakultet-list/fakultet-list.component').then(m => m.FakultetListComponent) },
  { path: 'fakultet/edit/:id', loadComponent: () => import('./fakultet/fakultet-form/fakultet-form.component').then(m => m.FakultetFormComponent) },
  { path: 'fakultet/add', loadComponent: () => import('./fakultet/fakultet-form/fakultet-form.component').then(m => m.FakultetFormComponent) },
  { path: 'departman', loadComponent: () => import('./departman/departman-list/departman-list.component').then(m => m.DepartmanListComponent) },
  { path: 'departman/edit/:id', loadComponent: () => import('./departman/departman-form/departman-form.component').then(m => m.DepartmanFormComponent) },
  { path: 'departman/add', loadComponent: () => import('./departman/departman-form/departman-form.component').then(m => m.DepartmanFormComponent) },
  { path: 'katedra', loadComponent: () => import('./katedra/katedra-list/katedra-list.component').then(m => m.KatedraListComponent) },
  { path: 'katedra/edit/:id', loadComponent: () => import('./katedra/katedra-form/katedra-form.component').then(m => m.KatedraFormComponent) },
  { path: 'katedra/add', loadComponent: () => import('./katedra/katedra-form/katedra-form.component').then(m => m.KatedraFormComponent) },
  { path: 'tip-studija', loadComponent: () => import('./tip-studija/tip-studija-list/tip-studija-list.component').then(m => m.TipStudijaListComponent) },
  
  { 
  path: 'tip-studija/edit/:uri',
  loadComponent: () =>
    import('./tip-studija/tip-studija-form/tip-studija-form.component').then(
      (m) => m.TipStudijaFormComponent
    ),
  },

  // { path: 'tip-studija/edit/:id', loadComponent: () => import('./tip-studija/tip-studija-form/tip-studija-form.component').then(m => m.TipStudijaFormComponent) },
  
  { path: 'tip-studija/add', loadComponent: () => import('./tip-studija/tip-studija-form/tip-studija-form.component').then(m => m.TipStudijaFormComponent) },
  { path: 'studijski-program', loadComponent: () => import('./studijski-program/studijski-program-list/studijski-program-list.component').then(m => m.StudijskiProgramListComponent) },
  { path: 'studijski-program/edit/:id', loadComponent: () => import('./studijski-program/studijski-program-form/studijski-program-form.component').then(m => m.StudijskiProgramFormComponent) },
  { path: 'studijski-program/add', loadComponent: () => import('./studijski-program/studijski-program-form/studijski-program-form.component').then(m => m.StudijskiProgramFormComponent) },
  { path: 'godina-studija', loadComponent: () => import('./godina-studija/godina-studija-list/godina-studija-list.component').then(m => m.GodinaStudijaListComponent) },
  { path: 'godina-studija/edit/:id', loadComponent: () => import('./godina-studija/godina-studija-form/godina-studija-form.component').then(m => m.GodinaStudijaFormComponent) },
  { path: 'godina-studija/add', loadComponent: () => import('./godina-studija/godina-studija-form/godina-studija-form.component').then(m => m.GodinaStudijaFormComponent) },
  { path: 'predmet', loadComponent: () => import('./predmet/predmet-list/predmet-list.component').then(m => m.PredmetListComponent) },
  { path: 'predmet/edit/:id', loadComponent: () => import('./predmet/predmet-form/predmet-form.component').then(m => m.PredmetFormComponent) },
  { path: 'predmet/add', loadComponent: () => import('./predmet/predmet-form/predmet-form.component').then(m => m.PredmetFormComponent) },
  { path: 'profesor-predmet', loadComponent: () => import('./profesor-predmet/profesor-predmet-list/profesor-predmet-list.component').then(m => m.ProfesorPredmetListComponent) },
  { path: 'profesor-predmet/edit/:id', loadComponent: () => import('./profesor-predmet/profesor-predmet-form/profesor-predmet-form.component').then(m => m.ProfesorPredmetFormComponent) },
  { path: 'profesor-predmet/add', loadComponent: () => import('./profesor-predmet/profesor-predmet-form/profesor-predmet-form.component').then(m => m.ProfesorPredmetFormComponent) },
  { path: 'moj-profil', loadComponent: () => import('./admin-profil/admin-profil.component').then(m => m.AdminProfilComponent) },

];
