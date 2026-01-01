import { Routes } from '@angular/router';
import { authGuard } from './core/guards/auth-guard';
export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  {
    path: 'home',
    loadComponent: () => import('./features/home/home').then(m => m.Home)
  },
  {
    path: 'about',
    loadComponent: () => import('./features/about/about').then(m => m.About)
  },
  {
    path: 'admin',
    loadComponent: () => import('./features/admin/admin').then(m => m.Admin),
    canActivate: [authGuard]
  }
];