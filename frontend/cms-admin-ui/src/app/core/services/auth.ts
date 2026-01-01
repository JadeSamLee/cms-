import { Injectable, signal, computed } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class Auth {
  // Private writable signal
  private loggedIn = signal<boolean>(false);

  // Public readonly signal (for components to read)
  isLoggedIn = this.loggedIn.asReadonly();

  // Optional: Computed signal for derived state (e.g., welcome message)
  welcomeMessage = computed(() => 
    this.loggedIn() ? 'Welcome, Admin!' : 'Please log in'
  );

  login() {
    this.loggedIn.set(true);  // Update signal value
  }

  logout() {
    this.loggedIn.set(false);
  }

  toggle() {
    this.loggedIn.update(current => !current);  // Toggle with update()
  }
}
// import { Injectable } from '@angular/core';
// import { BehaviorSubject } from 'rxjs';

// @Injectable({
//   providedIn: 'root'
// })
// export class AuthService {
//   private loggedIn = new BehaviorSubject<boolean>(false);
//   isLoggedIn$ = this.loggedIn.asObservable();

//   login() {
//     this.loggedIn.next(true);
//   }

//   logout() {
//     this.loggedIn.next(false);
//   }

//   isLoggedIn(): boolean {
//     return this.loggedIn.value;
//   }
// }