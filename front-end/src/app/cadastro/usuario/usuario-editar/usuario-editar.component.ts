import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { UsuarioInserirComponent } from '../usuario-inserir/usuario-inserir.component';
import { UsuarioService } from '../usuario.service';
import { Usuario } from '../usuario';

@Component({
  selector: 'app-usuario-editar',
  imports: [UsuarioInserirComponent],
  templateUrl: './usuario-editar.component.html',
  styleUrl: './usuario-editar.component.css'
})
export class UsuarioEditarComponent implements OnInit {

  usuario: Usuario = {
    id: 0,
    login: "",
    email: "",
    senha: ""
  };

  constructor(
    private service: UsuarioService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    const id = parseInt(this.route.snapshot.paramMap.get("id")!);
    this.usuario.id = id;
    this.buscarPorId(id);
  }

  buscarPorId(id: number) {
    this.service.buscarPorId(id).subscribe((u) => {
      this.usuario = u;
    });
  }

}
