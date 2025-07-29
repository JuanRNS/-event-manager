import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { PartyRegistrationComponent } from './components/party-registration/party-registration.component';
import { EventComponentsComponent } from './components/event-components/event-components.component';

export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'party-registration', component: PartyRegistrationComponent },
  { path: 'event-components', component: EventComponentsComponent },
  { path: '**', redirectTo: '/login' }
];


