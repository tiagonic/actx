import { Routes } from '@angular/router';
import { HomepageComponent } from './homepage/homepage.component';
import { UsuarioListarComponent } from './cadastro/usuario/usuario-listar/usuario-listar.component';
import { UsuarioPesquisarComponent } from './cadastro/usuario/usuario-pesquisar/usuario-pesquisar.component';
import { UsuarioInserirComponent } from './cadastro/usuario/usuario-inserir/usuario-inserir.component';
import { UsuarioEditarComponent } from './cadastro/usuario/usuario-editar/usuario-editar.component';
import { UsuarioExcluirComponent } from './cadastro/usuario/usuario-excluir/usuario-excluir.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'home',
    component: HomepageComponent
  },
  {
    path: 'usuario',
    component: UsuarioListarComponent
  },
  {
    path: 'usuario/pesquisar',
    component: UsuarioPesquisarComponent
  },
  {
    path: 'usuario/inserir',
    component: UsuarioInserirComponent
  },
  {
    path: 'usuario/editar/:id',
    component: UsuarioEditarComponent
  },
  {
    path: 'usuario/excluir/:id',
    component: UsuarioExcluirComponent
  }
];
