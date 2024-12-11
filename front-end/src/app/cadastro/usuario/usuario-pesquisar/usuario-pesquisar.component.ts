import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardActions } from '@angular/material/card';

import { UsuarioListarComponent } from "../usuario-listar/usuario-listar.component";
import { UsuarioService } from '../usuario.service';
import { Usuario } from '../usuario';

@Component({
  selector: 'app-usuario-pesquisar',
  imports: [
    CommonModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardActions,
    ReactiveFormsModule,
    UsuarioListarComponent
],
  templateUrl: './usuario-pesquisar.component.html',
  styleUrl: './usuario-pesquisar.component.css'
})
export class UsuarioPesquisarComponent implements OnInit, OnChanges{
  @Input() usuario: Usuario = {
    id:0,
    login:"",
    senha:"",
    email:""
  };

  usuarios: Usuario[] = [];

  userForm: FormGroup = new FormGroup({
    "login":new FormControl(this.usuario.login),
    "email": new FormControl(this.usuario.email),
    "senha": new FormControl(this.usuario.senha)
  });

  constructor(private service: UsuarioService){}

  ngOnInit(): void {
  }

  ngOnChanges(changes: SimpleChanges): void {
  }

  onSubmit() {
    this.usuario = this.userForm.value;
    this.service.pesquisar(this.usuario).subscribe((lista) => {
      this.usuarios = lista;
    });
  }
}
