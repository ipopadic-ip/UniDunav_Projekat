import { Routes } from '@angular/router';
import { SluzbenikDashboardComponent } from './sluzbenik-dashboard/sluzbenik-dashboard.component';

export const SLUZBENIK_ROUTES: Routes = [
  {
    path: '',
    component: SluzbenikDashboardComponent,
    children: [
      {
        path: 'trebovanje',
        loadComponent: () => import('./trebovanje/trebovanje.component')
          .then(m => m.TrebovanjeComponent)
      },
      {
  path: 'potvrde',
  loadComponent: () => import('./potvrde/potvrde.component')
    .then(m => m.PotvrdeComponent)
}
    ]
  }
];