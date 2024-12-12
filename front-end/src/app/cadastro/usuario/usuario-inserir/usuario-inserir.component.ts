import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule, DatePipe } from '@angular/common';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardActions } from '@angular/material/card';

import { UsuarioService } from '../usuario.service';
import { Usuario } from '../usuario';

@Component({
  selector: 'app-usuario-inserir',
  imports: [
    CommonModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardActions,
    ReactiveFormsModule
  ],
  templateUrl: './usuario-inserir.component.html',
  styleUrl: './usuario-inserir.component.css'
})
export class UsuarioInserirComponent implements OnInit, OnChanges {

  @Input() usuario: Usuario = {
    id: 0,
    login: "",
    email: "",
    senha: ""
  };

  userForm: FormGroup = new FormGroup({});

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private service: UsuarioService
  ) { }

  ngOnInit(): void {
    this.userForm = this.popularPersonForm();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (this.usuario?.id! > 0) {
      this.userForm = this.popularPersonForm();
    }
  }

  popularPersonForm(): FormGroup {
    this.userForm = this.formBuilder.group({
      id: [this.usuario.id],
      email: [this.usuario.email, [Validators.required, Validators.email]],
      login: [this.usuario.login, Validators.required],
      senha: [this.usuario.senha, Validators.required]
    });
    return this.userForm;
  }

  onSubmit() {
    if (this.userForm.valid) {
      this.usuario = this.userForm.value;
      this.service.inserir(this.usuario).subscribe((p) => {
        this.usuario = p;
        this.router.navigate(['/usuario'])
      });
    }
  }

  onLimpar() {
    this.usuario = {
      id: 0,
      login: "",
      email: "",
      senha: ""
    };
    this.userForm = this.popularPersonForm();
  }

  onCancelar() {
    window.history.back();
  }

}
