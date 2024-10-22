import { Component, ElementRef, ViewChild } from '@angular/core';
import { RouterLink } from '@angular/router';

declare var bootstrap: any;

@Component({
  selector: 'app-tutorial',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './tutorial.component.html',
  styleUrl: './tutorial.component.css'
})

export class TutorialComponent {
  
  @ViewChild('exampleModalLong', { static: false }) modal!: ElementRef;

  abrirModal() {
    const modal = new bootstrap.Modal(this.modal.nativeElement);
    modal.show();
  }

  cerrarModal() {
    const modal = new bootstrap.Modal(this.modal.nativeElement);
    modal.hide();
  }
}


