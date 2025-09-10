import { Routes } from '@angular/router';
import { LoginComponent } from './features/views/login/login.component';
import { authGuard } from './core/guards/auth.guard';
import { RegisterComponent } from './features/views/register/register.component';


export const routes: Routes = [
  { 
    path: '', 
    redirectTo: '/login', 
    pathMatch: 'full' 
  },
  { 
    path: 'login', 
    component: LoginComponent 
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  { 
    path: 'dashboard', 
    loadComponent: () => import('./features/views/dashboard/dashboard.component').then((m) => m.DashboardComponent),
    canActivate: []
  },
  { 
    path: 'party-registration', 
    loadComponent: () => import('./features/views/party-registration/party-registration.component').then((m) => m.PartyRegistrationComponent),
    canActivate: []
  },
  { 
    path: 'event-components', 
    loadComponent: () => import('./features/views/event-components/event-components.component').then((m) => m.EventComponentsComponent),
    canActivate: []
  },
  { 
    path: 'party-all-list', 
    loadComponent: () => import('./features/views/party-all-list/party-all-list.component').then((m) => m.PartyAllListComponent),
    canActivate: []
  },
  {
    path: 'dashboard-date',
    loadComponent: () => import('./features/views/dashboard-date/dashboard-date.component').then((m) => m.DashboardDateComponent),
    canActivate: []
  },
  { 
    path: '**', 
    redirectTo: '/login' 
  },
];


