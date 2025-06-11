import { Routes } from '@angular/router';
import { KomercijalistaDashboardComponent } from './komercijalista-dashboard/komercijalista-dashboard.component';

export const KOMERCIJALISTA_ROUTES: Routes = [
  {
    path: '',
    component: KomercijalistaDashboardComponent,
    children: [
      {
        path: 'trebovanje',
        loadComponent: () => import('./trebovanje-prikaz/trebovanje-prikaz.component')
          .then(m => m.TrebovanjePrikazComponent)
      },
      {
        path: 'dodavanje-knjiga',
          loadComponent: () => import('./dodavanje-knjiga/dodavanje-knjiga.component')
        .then(m => m.DodavanjeKnjigaComponent)
      }, 
      {
        path: 'profil',
        loadComponent: () => import('./komercijalista-profil/komercijalista-profil.component')
          .then(m => m.KomercijalistaProfilComponent)
      },
      {
        path: 'trebovanje-knjiga',
        loadComponent: () => import('./trebovanje-knjiga-prikaz/trebovanje-knjiga-prikaz.component')
          .then(m => m.TrebovanjeKnjigaPrikazComponent)
      }
    ]
  }
];