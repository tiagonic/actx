package br.com.actx.cadastro.usuario;

import br.com.actx.arquitetura.domain.dto.DataTransferObject;

public class UsuarioDTO extends DataTransferObject<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7687886646897515653L;

	private Long id;

	private String login;

	private String senha;

	private String email;

	public UsuarioDTO() {
		this(null);
	}

	public UsuarioDTO(UsuarioEntity entity) {
		if(entity!=null) {
			this.id = entity.getId();
			this.login = entity.getLogin();
			this.senha = entity.getSenha();
			this.email = entity.getEmail();
		}
	}

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
