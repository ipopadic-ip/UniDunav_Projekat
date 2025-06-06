import { Routes } from '@angular/router';
import { NastavnikDashboardComponent } from './nastavnik-dashboard/nastavnik-dashboard.component';
import { PregledPredmetaComponent } from './prikaz-predmeta/prikaz-predmeta.component';
import { ObavestenjaComponent } from './obavestenja/obavestenja.component';
import { ObavestenjeFormComponent } from './obavestenja/obavestenje-form.component';

export const profesorRoutes: Routes = [
  {
    path: '',
    component: NastavnikDashboardComponent,
    children: [
      { path: '', redirectTo: 'predmeti', pathMatch: 'full' },
      { path: 'predmeti', component: PregledPredmetaComponent },
      {
        path: 'silabus/:id',
        loadChildren: () => import('./silabus/silabus.routes').then(m => m.silabusRoutes)
      },
      {
        path: 'obavestenja',
        loadComponent: () => import('./obavestenja/obavestenja.component').then(m => m.ObavestenjaComponent)
      },
      {
        path: 'obavestenja/novo',
        component: ObavestenjeFormComponent
      },
      {
        path: 'obavestenja/izmeni/:id',
        component: ObavestenjeFormComponent
      },
      {
        path: 'studenti',
        loadComponent: () =>
          import('./studenti/studenti.component').then(m => m.StudentiComponent)
      }

    ]
  }
];
