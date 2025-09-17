import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [RouterModule, MatIconModule, MatToolbarModule, MatButtonModule],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.scss'
})
export class SidebarComponent {

  isSidebarOpen = false;

  constructor() { }

  toggleSidebar() {
    this.isSidebarOpen = !this.isSidebarOpen;
  }

  public logout() {
    localStorage.removeItem('token');
    window.location.reload();
  }
}

