package br.com.actx.cadastro.usuario;

import br.com.actx.arquitetura.domain.entity.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuario", schema = "public")
public class UsuarioEntity extends AbstractEntity<Long> {

	private static final long serialVersionUID = 410382444557254700L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String login;

	private String email;

	private String senha;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public void limpar() {
		this.id = null;
		this.login = null;
		this.email = null;
		this.senha = null;
	}

}
