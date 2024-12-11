import { Component, Input } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { UsuarioComponent } from '../usuario.component';

import { UsuarioService } from '../usuario.service';
import { Usuario } from '../usuario';

@Component({
  selector: 'app-usuario-listar',
  imports: [
    RouterModule,
    CommonModule,
    MatButtonModule,
    UsuarioComponent
  ],
  templateUrl: './usuario-listar.component.html',
  styleUrl: './usuario-listar.component.css'
})
export class UsuarioListarComponent {

  @Input() usuarios: Usuario[] = [];

  constructor(private service: UsuarioService) {
  }

  ngOnInit(): void {
    this.service.listar().subscribe((lista) => {
      this.usuarios = lista;
    });
  }

  async loadUsuarios(): Promise<void> {
    const response = await fetch(this.service.getAPI());
    const usuarios = await response.json();
    this.usuarios = usuarios;
  }

}
