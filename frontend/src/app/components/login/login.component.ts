import { Component, Output, EventEmitter } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  @Output() close = new EventEmitter<void>();
  @Output() switchToSignUp = new EventEmitter<void>();

  // Simulación de un método de inicio de sesión (puedes modificarlo según tu lógica)
  onLogin() {
    // Lógica para iniciar sesión...
    // Si el inicio de sesión es exitoso, puedes emitir un evento o cerrar el modal.
    this.close.emit(); // Emitir evento al cerrar
  }

  onClose() {
    this.close.emit(); // Emitir evento para cerrar el modal
  }

  onSwitchToSignUp() {
    this.switchToSignUp.emit(); // Emitir el evento al cambiar a registrarse
  }
}
