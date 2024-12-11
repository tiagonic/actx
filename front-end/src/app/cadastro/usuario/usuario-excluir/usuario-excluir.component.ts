import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { MatDialogActions, MatDialogContent, MatDialogTitle, MatDialogClose, MatDialogRef } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button'

import { UsuarioService } from '../usuario.service';
import { Usuario } from '../usuario';

@Component({
  selector: 'app-usuario-excluir',
  imports: [MatDialogContent, MatDialogActions, MatDialogTitle, MatButtonModule, MatDialogClose],
  templateUrl: './usuario-excluir.component.html',
  styleUrl: './usuario-excluir.component.css'
})
export class UsuarioExcluirComponent implements OnInit {

  usuario: Usuario = {
    id: 0,
    email: "",
    login: "",
    senha: ""
  };

  constructor(
    private service: UsuarioService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.usuario.id = parseInt(this.route.snapshot.paramMap.get("id")!);
  }

  excluir(): void {
    this.service.deletar(this.usuario).subscribe((user) => {
      this.router.navigate(['/usuario']);
    });
  }

  cancelar(): void {
    this.router.navigate(['/usuario']);
  }

}
