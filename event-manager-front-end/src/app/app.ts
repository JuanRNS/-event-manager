import { Component } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { SidebarComponent } from "./core/components/sidebar/sidebar.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, SidebarComponent],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  public title = 'Party Manager';

  constructor(
    private readonly _route: Router
  ) { }

  public showSidebar() {
    return this._route.url !== '/login' && this._route.url !== '/register' && !this._route.url.startsWith('/calendar');
  }
}
