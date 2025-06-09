import { Routes } from '@angular/router';
import { StudentDashboardComponent } from './student-dashboard/student-dashboard.component';
import { PregledPredmetaComponent } from './pregled_predmeta/pregled-predmeta.component';

export const STUDENT_ROUTES: Routes = [
  {
    path: '',
    component: StudentDashboardComponent,
    children: [
      { path: 'predmeti', component: PregledPredmetaComponent },
      {
        path: 'obavestenja',
        loadComponent: () => import('./obavestenja/obavestenja.component')
          .then(m => m.ObavestenjaComponent)
      },
      {
        path: 'prijava',
        loadComponent: () => import('./prijava_ispita/prijava-ispita.component')
          .then(m => m.PrijavaIspitaComponent)
      },
      {
        path: 'istorija',
        loadComponent: () => import('./istorija-studiranja/istorija-studiranja.component')
        .then(m => m.IstorijaStudiranjaComponent)
      }
    ]
  }
];