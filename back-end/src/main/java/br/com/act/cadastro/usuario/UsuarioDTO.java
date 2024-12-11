// UsuarioDTO.java

package br.com.act.cadastro.usuario;

public class UsuarioDTO {

    private Long id;

    private String nome;

    private String email;
    
    public UsuarioDTO() {
    	this(null);
    }

    public UsuarioDTO(UsuarioEntity entity) {
    	this.id = entity.getId();
    	this.nome = entity.getNome();
    	this.email = entity.getEmail();
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
