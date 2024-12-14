/**
 * DAO.java
 * 
 * Esta interface é uma API para implementar o padrão Data Access Object (DAO).
 * 
 * O padrão DAO é usado para abstrair e encapsular todas as interações com uma fonte de dados,
 * oferecendo uma API para as operações de persistência. A sigla DAO significa Data Access Object.
 * 
 * O objetivo principal desta abstração é fornecer uma interface uniforme para operações de 
 * CRUD (Create, Read, Update, Delete) e, assim, promover a reutilização de código, aumentando
 * a coesão e diminuindo o acoplamento dos componentes do sistema.
 * 
 * @param <T> Tipo da entidade.
 * @param <ID> Tipo da chave primária da entidade.
 * 
 * Desenvolvedor: Tiago Barreto dos Santos
 * E-mail: tiagonic@gmail.com
 * GitHub: https://github.com/tiagonic
 * Data: 04 de Dezembro de 2024
 */
package br.com.actx.arquitetura.dao;

import java.io.Serializable;
import java.util.List;

import br.com.actx.arquitetura.domain.entity.AbstractEntity;

public interface DAO<ID extends Serializable, T extends AbstractEntity<ID>> {

	public void persist(T entity);

	public void detach(T entity);

	public T findById(ID id);

	public T findEntity(T entity);

	public List<T> findAll();

	public void remove(T entity);

	public List<T> pesquisar(T entity);
}
