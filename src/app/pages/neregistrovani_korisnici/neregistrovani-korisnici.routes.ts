import { Routes } from '@angular/router';

export const neregistrovaniKorisniciRoutes: Routes = [
  { path: '', loadComponent: () => import('./univerzitet/univerzitet.component').then(m => m.UniverzitetComponent) },
  { path: 'fakulteti', loadComponent: () => import('./fakulteti/fakulteti.component').then(m => m.FakultetiComponent) },
  { path: 'fakulteti/:id', loadComponent: () => import('./fakultet-detalji/fakultet-detalji.component').then(m => m.FakultetDetaljiComponent) },
  { path: 'studijski-programi', loadComponent: () => import('./studijski-programi/studijski-programi.component').then(m => m.StudijskiProgramiComponent) }, 
  { path: 'studijski-programi/:id', loadComponent: () => import('./studijski-program-detalji/studijski-program-detalji.component').then(m => m.StudijskiProgramDetaljiComponent) },
  {
  path: 'departmani/:id', loadComponent: () => import('./departman-detalji/departman-detalji.component').then(m => m.DepartmanDetaljiComponent)}

];
