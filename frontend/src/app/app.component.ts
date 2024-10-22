import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './components/login/login.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule, RouterLink, RouterLinkActive, LoginComponent, SignUpComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';
  isLoggedIn: boolean = false;
  showLogIn: boolean = false;
  showSignUp: boolean = false;

  toggleLogin(){
    this.showLogIn = !this.showLogIn;
    this.showSignUp = false;
  }

  toggleSignUp(){
    this.showSignUp = !this.showSignUp;
    this.showLogIn = false;
  }
  
  switchToSignUp() {
    this.toggleSignUp(); // Muestra el formulario de registro
  }

  switchToLogin() {
    this.toggleLogin(); // Muestra el formulario de inicio de sesión
  }
}
