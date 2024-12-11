package br.com.act.cadastro.usuario;
import java.util.ArrayList;

import br.com.act.arquitetura.controller.AbstractCadastroBean;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.inject.Named;

@Named
@ConversationScoped
public class CadastroUsuarioBean extends AbstractCadastroBean<UsuarioEntity> {
	private static final long serialVersionUID = 3L;

	@EJB
	private UsuarioService service;

	@PostConstruct
	private void onEntity() {
		super.entity = new UsuarioEntity();
		super.entitySelecionada = super.entity;
	}

	public String novo() {
		this.limpar();
		return "inserir?faces-redirect=true";
	}

	public String editar() {
		if (entity != null) {
			return "inserir?faces-redirect=true";
		} else {
			this.addWarnMessage("Nenhum usuário selecionado!", "Selecione um usuário para editar.");
			return null;
		}
	}

	public String salvar() {
		this.iniciarConversa();
		try {
			service.inserir(entity);
			this.addInfoMessage("Cadastro realizado com sucesso!");

			if(entities == null) {
				entities = new ArrayList<UsuarioEntity>();
			}
			entities.clear();
			entities.add(entity);

		} catch (Exception e) {
			this.finalizarConversa();
			this.addErrorMessage("Erro ao realizar cadastro!", e.getMessage());
		}

		return "pesquisar?faces-redirect=true";
	}

	public String cancelar() {
		this.limpar();
		return "pesquisar";
	}

	public void excluir() {
		try {
			service.excluir(entity);
			this.addInfoMessage("Usuário excluído com sucesso!");

			if(entities != null && entities.size() > 0) {
				entities.remove(entity);
				if(entities.size() == 0) {
					this.finalizarConversa();
				}
			}

			entity.limpar();

		} catch (Exception e) {
			this.addErrorMessage("Erro ao tentar excluir usuário!", e.getMessage());
			this.finalizarConversa();
		}

	}

	public void pesquisar() {
		try {
			entities = service.pesquisar(entity);
			if(entities.size() > 0) {
				this.addInfoMessage(entities.size() + " registro(s) encontrado(s)!");
				this.iniciarConversa();
			} else {
				this.addInfoMessage("Nenhum registro encontrado!");
			}
		} catch (Exception e) {
			this.finalizarConversa();
			this.addErrorMessage("Erro ao realizar pesquisa!", e.getMessage());
		}
	}

	public void listar() {
		entities = service.listar();
	}

	public void limpar() {
		entity.limpar();
		entitySelecionada = entity;
		if(entities != null) {
			entities.clear();
		}
		this.finalizarConversa();
	}

}
