package br.com.act.cadastro.usuario;

import java.util.ArrayList;
import java.util.List;

import br.com.act.arquitetura.dao.DataAccessObject;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Stateless
public class UsuarioDAO extends DataAccessObject<Long, UsuarioEntity> {
	private EntityManager entityManager;

	@PostConstruct
	private void onEntityManager() {
		this.entityManager = super.getEntityManager();
	}

	public List<UsuarioEntity> pesquisar(UsuarioEntity entity) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UsuarioEntity> cq = cb.createQuery(UsuarioEntity.class);
		Root<UsuarioEntity> root = cq.from(UsuarioEntity.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (entity.getNome() != null && !entity.getNome().isEmpty()) {
			predicates.add(cb.like(cb.lower(root.get("nome")), "%" + entity.getNome().toLowerCase() + "%"));
		}

		if (entity.getEmail() != null && !entity.getEmail().isEmpty()) {
			predicates.add(cb.like(cb.lower(root.get("email")), "%" + entity.getEmail().toLowerCase() + "%"));
		}

		cq.where(predicates.toArray(new Predicate[0]));

		TypedQuery<UsuarioEntity> query = entityManager.createQuery(cq);
		return query.getResultList();
	}
}
