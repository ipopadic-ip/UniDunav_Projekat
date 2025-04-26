import { Routes } from '@angular/router';
import { NeregistrovaniKorisniciComponent } from './pages/neregistrovani_korisnici/neregistrovani-korisnici.component';
import { PrijavaComponent } from './pages/prijava/prijava.component';

export const routes: Routes = [
  {
    path: '',
    component: NeregistrovaniKorisniciComponent, // Ovo je sada parent komponenta
    loadChildren: () => import('./pages/neregistrovani_korisnici/neregistrovani-korisnici.routes')
      .then(m => m.neregistrovaniKorisniciRoutes)
  },
  { path: 'prijava', component: PrijavaComponent },
  {
    path: 'admin',
    loadComponent: () => import('./pages/administrator/admin-dashboard/admin-dashboard.component').then(m => m.AdminDashboardComponent)
  },
  {
    path: 'nastavnik',
    loadComponent: () => import('./pages/nastavnici/nastavnik-dashboard/nastavnik-dashboard.component').then(m => m.NastavnikDashboardComponent)
  },
  {
    path: 'student',
    loadComponent: () => import('./pages/studenti/student-dashboard/student-dashboard.component').then(m => m.StudentDashboardComponent)
  },
  {
    path: 'sluzba',
    loadComponent: () => import('./pages/studentska_služba/sluzba-dashboard/sluzba-dashboard.component').then(m => m.SluzbaDashboardComponent)
  },
];

