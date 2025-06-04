import { Routes } from '@angular/router';

export const neregistrovaniKorisniciRoutes: Routes = [
  { path: '', loadComponent: () => import('./univerzitet/univerzitet.component').then(m => m.UniverzitetComponent) },
  { path: 'fakulteti', loadComponent: () => import('./fakulteti/fakulteti.component').then(m => m.FakultetiComponent) },
  { path: 'fakulteti/:id', loadComponent: () => import('./fakultet-detalji/fakultet-detalji.component').then(m => m.FakultetDetaljiComponent) },
  { path: 'studijskiprogram/:id', loadComponent: () => import('./studijski-program-detalji/studijski-program-detalji.component').then(m => m.StudijskiProgramDetaljiComponent) },
  {path: 'departmani/:id', loadComponent: () => import('./departman-detalji/departman-detalji.component').then(m => m.DepartmanDetaljiComponent)},
  { path: 'katedre/:id', loadComponent: () => import('./katedra-detalji/katedra-detalji.component').then(m => m.KatedraDetaljiComponent)},
  { path: 'predmeti/:id', loadComponent: () => import('./predmet-detalji/predmet-detalji.component').then(m => m.PredmetDetaljiComponent)},
  {path: 'opsta-obavestenja',loadComponent: () =>import('./opsta-obavestenja/opsta-obavestenja.component').then(m => m.OpstaObavestenjaComponent)
}


];
