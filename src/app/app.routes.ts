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
  { path: 'prijava', component: PrijavaComponent }
];

