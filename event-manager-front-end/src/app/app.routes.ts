import { Routes } from '@angular/router';
import { LoginComponent } from './features/views/login/login.component';
import { DashboardComponent } from './features/views/dashboard/dashboard.component';
import { PartyRegistrationComponent } from './features/views/party-registration/party-registration.component';
import { EventComponentsComponent } from './features/views/event-components/event-components.component';
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
    component: DashboardComponent, 
    canActivate: [authGuard]
  },
  { 
    path: 'party-registration', 
    component: PartyRegistrationComponent, 
    canActivate: [authGuard]
  },
  { 
    path: 'event-components', 
    component: EventComponentsComponent,
    canActivate: [authGuard]
  },
  { 
    path: '**', 
    redirectTo: '/login' 
  },
];


