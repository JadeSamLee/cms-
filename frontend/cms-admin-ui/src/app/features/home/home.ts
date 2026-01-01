import { Component } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { Auth } from '../../core/services/auth';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [CardModule, ButtonModule],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {
  constructor(private authService: Auth, private router: Router) {}

  login() {
    this.authService.login();
    this.router.navigate(['/admin']);
  }
}
