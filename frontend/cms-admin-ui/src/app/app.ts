import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MenubarModule } from 'primeng/menubar';
import { ButtonModule } from 'primeng/button';
import { MenuItem } from 'primeng/api';
import { Auth } from './core/services/auth';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})

export class App {
  menuItems: MenuItem[] = [];

  constructor(public authService: Auth, private router: Router) {
    this.updateMenu();
    this.authService.isLoggedIn$.subscribe(() => this.updateMenu());
  }

  updateMenu() {
    this.menuItems = [
      { label: 'Home', icon: 'pi pi-home', routerLink: ['/home'] },
      { label: 'About', icon: 'pi pi-info-circle', routerLink: ['/about'] },
      {
        label: 'Admin Dashboard',
        icon: 'pi pi-shield',
        routerLink: ['/admin'],
        visible: this.authService.isLoggedIn()
      }
    ];
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/home']);
  }
}