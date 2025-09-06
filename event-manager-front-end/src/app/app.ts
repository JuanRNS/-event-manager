import { Component, signal } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { SidebarComponent } from "./core/components/sidebar/sidebar.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, SidebarComponent],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('Balloons Recepções');

  constructor(
    private readonly _route: Router
  ) { }

  public showSidebar() {
    return this._route.url !== '/login' && this._route.url !== '/register';
  }
}
