package org.generation.blogPessoal.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import java.util.Optional;
import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@BeforeAll
	void start() {
		usuarioRepository.save(new Usuario(0L, "Gil Brother Ribeiro",
				"https://yt3.ggpht.com/ytc/AKedOLQ7rS8ts2GXNV2Dpn568gwhX7wN4fLait4R8MqPWA=s900-c-k-c0x00ffffff-no-rj",
				"gilbrother@email.com.br", "13456789"));
		usuarioRepository.save(new Usuario(0L, "Hebert Conceição Ribeiro",
				"https://s2.glbimg.com/QKrtPimu8fsEe-pFGZkv90B9vAo=/smart/e.glbimg.com/og/ed/f/original/2021/08/04/hebert_conceicao.jpeg",
				"conceicao@email.com.br", "13456789"));
		usuarioRepository.save(new Usuario(0L, "Gabriel Ribeiro",
				"https://conteudo.imguol.com.br/c/esporte/ea/2021/06/14/gabriel-volante-do-corinthians-comemora-gol-contra-o-palmeiras-pelo-brasileirao-1623685293390_v2_4x3.jpg",
				"gabriel@email.com.br", "13456789"));
		usuarioRepository.save(new Usuario(0L, "David Ribeiro",
				"https://istoe.com.br/wp-content/uploads/sites/14/2021/05/david-by-michelangelo-660x372-1.jpg",
				"David@email.com.br", "13456789"));
		usuarioRepository.save(new Usuario(0L, "Bruno Ribeiro",
				"https://scontent.fcgh38-1.fna.fbcdn.net/v/t39.30808-6/272832684_668699164578010_3248030158597448154_n.jpg?_nc_cat=103&ccb=1-5&_nc_sid=09cbfe&_nc_eui2=AeF3PAGlvGGBndE_IrXcpV-AHPWdeDShy9Yc9Z14NKHL1p4TDbVTTKKapmvKW4M7VgYW0lxZW0d1Op9Ih5CVmDnf&_nc_ohc=qRlD2sEWATMAX_hIvgY&_nc_ht=scontent.fcgh38-1.fna&oh=00_AT-VoZjzuQIeHTtShzE7EYIpmcxqH6DvgkLNHp68YpMpGA&oe=6203D6BC",
				"Bruno@email.com.br", "13456789"));
		usuarioRepository.save(new Usuario(0L, "Thiago",
				"https://conteudo.imguol.com.br/c/esporte/68/2020/09/21/thiago-alcantara-fez-sua-estreia-pelo-liverpool-na-vitoria-sobre-o-chelsea-1600707376908_v2_4x3.jpg",
				"thiago@email.com.br", "13456789"));
	}

	@Test
	@DisplayName("Retorna 1 usuário")
	public void deveRetornarUmUsuario() {
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("gabriel@email.com.br");
		assertTrue(usuario.get().getUsuario().equals("gabriel@email.com.br"));
	}

	@Test
	@DisplayName("Retorna 5 usuários")
	public void deveRetornarCincoUsuario() {
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Ribeiro");
		assertEquals(5, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Gil Brother Ribeiro"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Conceição Ribeiro"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Gabriel Ribeiro"));
		assertTrue(listaDeUsuarios.get(3).getNome().equals("David Ribeiro"));
		assertTrue(listaDeUsuarios.get(4).getNome().equals("Bruno Ribeiro"));
	}

	@Test
	@DisplayName("Usuário por ID")
	public void deveRetornarPorId() {
		Optional<Usuario> porId = usuarioRepository.findById(1L);
		assertTrue(porId.get().getUsuario().equals("gilbrother@email.com.br"));
	}

	@Test
	@DisplayName("Usuário inexistente")
	public void naoDeveExistir() {
		Optional<Usuario> porId = usuarioRepository.findById(100L);
		assertTrue(porId.isEmpty());
	}
}
