import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from './usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  private readonly API: string = "http://localhost:8080/act/api/usuarios";

  constructor(private http: HttpClient) { }

  getAPI(): string {
    return this.API;
  }

  inserir(usuario: Usuario): Observable<Usuario> {
    if (usuario != null && usuario.id != null && usuario.id > 0) {
      return this.atualizar(usuario);
    } else {
      const url = `${this.API}/inserir/`
      return this.http.post<Usuario>(url, usuario);
    }
  }

  atualizar(usuario: Usuario): Observable<Usuario> {
    const url = `${this.API}/${usuario.id}`
    return this.http.put<Usuario>(url, usuario);
  }

  deletar(usuario: Usuario): Observable<Usuario> {
    const url = `${this.API}/${usuario.id}`
    return this.http.delete<Usuario>(url);
  }

  buscarPorId(id: number): Observable<Usuario> {
    const url = `${this.API}/${id}`
    return this.http.get<Usuario>(url);
  }

  pesquisar(usuario: Usuario): Observable<Usuario[]> {
    const url = `${this.API}/pesquisar/`
    return this.http.post<Usuario[]>(url, usuario);
  }

  listar(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(this.API);
  }
}
