package br.com.actx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.actx.cadastro.usuario.UsuarioDAO;
import br.com.actx.cadastro.usuario.UsuarioEntity;
import br.com.actx.cadastro.usuario.UsuarioService;

public class UsuarioServiceTest {

	@Mock
	private UsuarioDAO usuarioDAO;

	@InjectMocks
	private UsuarioService usuarioService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testListar() {
		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setId(1L);
		List<UsuarioEntity> expectedUsuarios = Collections.singletonList(usuario);
		when(usuarioDAO.findAll()).thenReturn(expectedUsuarios);
		List<UsuarioEntity> actualUsuarios = usuarioService.listar();
		assertEquals(expectedUsuarios, actualUsuarios);
	}

	@Test
	public void testInserir() {
		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setId(1L);
		usuarioService.inserir(usuario);
		verify(usuarioDAO, times(1)).persist(usuario);
	}

	@Test
	public void testExcluir() {
		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setId(1L);
		usuarioService.excluir(usuario);
		verify(usuarioDAO, times(1)).remove(usuario);
	}
}
