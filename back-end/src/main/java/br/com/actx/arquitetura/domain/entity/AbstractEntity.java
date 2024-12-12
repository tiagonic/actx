/**
 * AbstractEntity.java
 * 
 * Esta classe representa uma entidade abstrata que serve como base para todas as entidades JPA do projeto.
 * Ela fornece atributos comuns como `id`, `dataCriacao` e `dataAtualizacao`, além de métodos que definem
 * o comportamento padrão durante a criação e atualização das entidades.
 * 
 * Ao utilizar esta classe como base, todas as entidades do projeto herdarão automaticamente esses atributos
 * e comportamentos, promovendo a reutilização de código, a consistência e a facilidade de manutenção.
 * 
 * @param <ID> Tipo da chave primária da entidade.
 * 
 * @autor Tiago Barreto dos Santos
 * @github https://github.com/tiagonic
 * @projeto https://github.com/tiagonic/actx.git
 * 
 * Data: 12 de Dezembro de 2024
 */

package br.com.actx.arquitetura.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import br.com.actx.arquitetura.common.Identifiable;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@MappedSuperclass
public abstract class AbstractEntity<ID extends Serializable> implements Identifiable<ID> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private ID id;

	@Column(name = "data_criacao", updatable = false)
	private LocalDateTime dataCriacao;

	@Column(name = "data_atualizacao")
	private LocalDateTime dataAtualizacao;

	@PrePersist
	protected void onCreate() {
		dataCriacao = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		dataAtualizacao = LocalDateTime.now();
	}

	@Override
	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public LocalDateTime getDataAtualizacao() {
		return dataAtualizacao;
	}

	abstract public void limpar();
}

