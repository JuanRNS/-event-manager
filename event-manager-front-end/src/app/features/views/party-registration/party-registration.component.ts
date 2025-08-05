import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SidebarComponent } from '../../../core/components/sidebar/sidebar.component';

@Component({
  selector: 'app-party-registration',
  standalone: true,
  imports: [FormsModule, CommonModule, SidebarComponent],
  templateUrl: './party-registration.component.html',
  styleUrl: './party-registration.component.scss'
})
export class PartyRegistrationComponent {
 
}

