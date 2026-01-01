import { Component } from '@angular/core';
import { SidebarModule } from 'primeng/sidebar';
import { Auth } from '../../../core/services/auth';
import { ButtonModule } from 'primeng/button';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [SidebarModule, ButtonModule],
  templateUrl: './sidebar.html',
  styleUrl: './sidebar.css',
  
})
export class SidebarComponent {
  constructor(public authService: Auth) {}
}