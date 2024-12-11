package br.com.act.cadastro.usuario;

import java.util.List;

import br.com.act.arquitetura.service.AbstractService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class UsuarioService extends AbstractService<Long, UsuarioEntity, UsuarioDAO> {

	@EJB
	private UsuarioDAO usuarioDAO;
	
	@PostConstruct
	private void onDAO() {
		super.setDao(usuarioDAO);
	}

	public void inserir(UsuarioEntity entity) {
		usuarioDAO.persist(entity);
	}

	public void excluir(UsuarioEntity entity) {
		usuarioDAO.remove(entity);
	}

	public List<UsuarioEntity> listar() {
		return usuarioDAO.findAll();
	}

	public List<UsuarioEntity> pesquisar(UsuarioEntity entity) {
		return usuarioDAO.pesquisar(entity);
	}
}
