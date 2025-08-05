import { Routes } from '@angular/router';
import { LoginComponent } from './features/views/login/login.component';
import { DashboardComponent } from './features/views/dashboard/dashboard.component';
import { PartyRegistrationComponent } from './features/views/party-registration/party-registration.component';
import { EventComponentsComponent } from './features/views/event-components/event-components.component';


export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'party-registration', component: PartyRegistrationComponent },
  { path: 'event-components', component: EventComponentsComponent },
  { path: '**', redirectTo: '/login' }
];


