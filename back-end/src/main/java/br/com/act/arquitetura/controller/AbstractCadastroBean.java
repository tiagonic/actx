package br.com.act.arquitetura.controller;

import java.io.Serializable;
import java.util.List;

import br.com.act.arquitetura.entity.AbstractEntity;
import br.com.act.arquitetura.util.MessagesUtil;
import jakarta.enterprise.context.Conversation;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.inject.Inject;

@ConversationScoped
public abstract class AbstractCadastroBean<T extends AbstractEntity<?>> implements Serializable {

	private static final long serialVersionUID = 2L;

	@Inject
	protected Conversation conversation;

	protected T entity, entitySelecionada;
	protected List<T> entities;

	public void iniciarConversa() {
		if (conversation.isTransient()) {
			conversation.begin();
		}
	}

	public void finalizarConversa() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
	}

	public void addInfoMessage(String summary) {
		MessagesUtil.addInfoMessage(summary);
	}

	public void addErrorMessage(String summary, String detail) {
		MessagesUtil.addErrorMessage(summary, detail);
	}

	public void addWarnMessage(String summary, String detail) {
		MessagesUtil.addWarnMessage(summary, detail);
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public T getEntitySelecionada() {
		return entitySelecionada;
	}

	public void setEntitySelecionada(T entitySelecionada) {
		this.entitySelecionada = entitySelecionada;
	}

	public List<T> getEntities() {
		return entities;
	}

	public void setEntities(List<T> entities) {
		this.entities = entities;
	}

	protected abstract String novo();
	protected abstract String editar();
	protected abstract String salvar();
	protected abstract String cancelar();
	protected abstract void excluir();
	protected abstract void pesquisar();
	protected abstract void limpar();
}
