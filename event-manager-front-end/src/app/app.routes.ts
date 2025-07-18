import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login';
import { PartyRegistrationComponent } from './components/party-registration/party-registration';
import { EventComponentsComponent } from './components/event-components/event-components';
import { DashboardComponent } from './components/dashboard/dashboard';

export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'party-registration', component: PartyRegistrationComponent },
  { path: 'event-components', component: EventComponentsComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: '**', redirectTo: '/login' }
];

