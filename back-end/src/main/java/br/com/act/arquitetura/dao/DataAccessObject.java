/**
 * DataAccessObject.java
 * 
 * Esta classe representa uma implementação abstrata do padrão Data Access Object (DAO).
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
package br.com.act.arquitetura.dao;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import br.com.act.arquitetura.entity.AbstractEntity;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public abstract class DataAccessObject<ID extends Serializable, T extends AbstractEntity<ID>> implements DAO<ID, T> {

	@PersistenceContext
	private EntityManager entityManager;

	private Class<T> entityClass;

	public DataAccessObject() {
		this(null);
	}

	public DataAccessObject(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	@PostConstruct
	private void init() {
		this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}

	public void persist(T entity) {
		if (entity.getId() == null) {
			entityManager.persist(entity);
		} else {
			entityManager.merge(entity);
		}
	}

	public void detach(T entity) {
		if (entity != null && entity.getId() != null) {
			entityManager.detach(entity);
		} 
	}

	public T findById(ID id) {
		return entityManager.find(entityClass, id);
	}

	@Override
	public T findEntity(T entity) {
		return this.findById(entity.getId());
	}

	public List<T> findAll() {
		return entityManager.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass).getResultList();
	}

	public void remove(T entity) {
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	protected void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
