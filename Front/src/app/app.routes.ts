// import { Routes } from '@angular/router';

// export const routes: Routes = [];

// import { NgModule } from '@angular/core';
import { Routes } from '@angular/router';
import { UniverzitetComponent } from './pages/univerzitet/univerzitet.component';
import { FakultetiComponent } from './pages/fakulteti/fakulteti.component';
import { StudijskiProgramiComponent } from './pages/studijski-programi/studijski-programi.component';
import { PrijavaComponent } from './pages/prijava/prijava.component';

export const routes: Routes = [
  { path: '', component: UniverzitetComponent },
  { path: 'fakulteti', component: FakultetiComponent },
  { path: 'studijski-programi', component: StudijskiProgramiComponent },
  { path: 'prijava', component: PrijavaComponent }
];

