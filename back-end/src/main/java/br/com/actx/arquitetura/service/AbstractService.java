/**
 * AbstractService.java
 * 
 * Esta classe abstrata representa a camada de serviço genérica para a aplicação.
 * Ela fornece operações comuns de CRUD (Create, Read, Update, Delete) e define um padrão
 * para os serviços específicos que herdam desta classe.
 * 
 * A ideia principal é centralizar a lógica de negócios comum, promovendo a reutilização de
 * código, aumentando a coesão e reduzindo o acoplamento das camadas de serviço.
 * 
 * @param <T> Tipo da entidade que estende AbstractEntity.
 * @param <DAO> Tipo do DAO que implementa GenericDAO.
 * 
 * Desenvolvedor: Tiago Barreto dos Santos
 * E-mail: tiagonic@gmail.com
 * GitHub: https://github.com/tiagonic
 * Data: 05 de Dezembro de 2024
 */

package br.com.actx.arquitetura.service;

import java.io.Serializable;
import java.util.List;

import br.com.actx.arquitetura.dao.DAO;
import br.com.actx.arquitetura.domain.entity.AbstractEntity;

public abstract class AbstractService<ID extends Serializable, T extends AbstractEntity<ID>, D extends DAO<ID, T>> {

	protected D dao;

	public void inserir(T entity) {
		dao.persist(entity);
	}

	public void excluir(T entity) {
		dao.remove(entity);
	}

	public T buscarPorId(ID id) {
		return dao.findById(id);
	}

	public List<T> listar() {
		return dao.findAll();
	}

	public List<T> pesquisar(T entity) {
		return dao.pesquisar(entity);
	}

	public D getDao() {
		return dao;
	}

	public void setDao(D dao) {
		this.dao = dao;
	}

}
