import { Component, Output, EventEmitter } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.css'
})
export class SignUpComponent {
  @Output() close = new EventEmitter<void>();
  @Output() switchToLogin = new EventEmitter<void>();
  
    // Simulación de un método de inicio de sesión (puedes modificarlo según tu lógica)
    onSignup() {
      // Lógica para iniciar sesión...
      // Si el inicio de sesión es exitoso, puedes emitir un evento o cerrar el modal.
      this.close.emit(); // Emitir evento al cerrar
    }
  
    onClose() {
      this.close.emit(); // Emitir evento para cerrar el modal
    }

    onSwitchToLogin() {
      this.switchToLogin.emit(); // Emitir el evento al cambiar a iniciar sesión
    }  
}
