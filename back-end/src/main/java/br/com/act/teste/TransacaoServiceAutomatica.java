package br.com.act.teste;

import java.util.List;

import br.com.act.cadastro.usuario.UsuarioDAO;
import br.com.act.cadastro.usuario.UsuarioEntity;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class TransacaoServiceAutomatica {

	@EJB
	private UsuarioDAO usuarioDAO;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void transacaoRequerida(UsuarioEntity entity) {
		usuarioDAO.persist(entity);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void transacaoRequerNova(UsuarioEntity entity) {
		usuarioDAO.persist(entity);
	}

	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void transacaoObrigatoria(UsuarioEntity entity) {
		usuarioDAO.persist(entity);
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public UsuarioEntity transacaoSuporta(Long id) {
		return usuarioDAO.findById(id);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<UsuarioEntity> transacaoNaoSuporta() {
		return usuarioDAO.findAll();
	}

	@TransactionAttribute(TransactionAttributeType.NEVER)
	public void transacaoNunca(UsuarioEntity entity) {
		usuarioDAO.persist(entity);
	}
}
