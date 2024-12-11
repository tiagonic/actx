import { Component, Input } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatCard, MatCardActions, MatCardHeader, MatCardSubtitle, MatCardTitle } from '@angular/material/card';
import { Usuario } from './usuario';

@Component({
  selector: 'app-usuario',
  imports: [
    RouterModule,
    CommonModule,
    MatButtonModule, MatCard, MatCardHeader, MatCardTitle, MatCardSubtitle, MatCardActions
  ],
  templateUrl: './usuario.component.html',
  styleUrl: './usuario.component.css'
})
export class UsuarioComponent {
  @Input() usuario: Usuario = {
    id:0,
    login:"",
    senha:"",
    email:""
  };
}
