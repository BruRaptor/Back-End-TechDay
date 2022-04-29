package org.generation.blogPessoal.controller;

import static org.junit.Assert.assertEquals;

import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.service.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private UsuarioService service;

	@Test
	@Order(1)
	@DisplayName("Cadastrar um Usuário")
	public void deveCriarUmUsuario() {
		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(new Usuario(0L, "Sarah Ribeiro",
				"https://upload.wikimedia.org/wikipedia/commons/thumb/e/ec/Sunset_in_Manaus.jpg/640px-Sunset_in_Manaus.jpg",
				"sarah@email.com", "123456789"));
		ResponseEntity<Usuario> resposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, requisicao,
				Usuario.class);

		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
		assertEquals(requisicao.getBody().getNome(), requisicao.getBody().getNome());
		assertEquals(requisicao.getBody().getUsuario(), resposta.getBody().getUsuario());
	}

	@Test
	@Order(2)
	@DisplayName("Não deve permitir duplicação fo Usuário")
	public void naoDeveDuplicarUsuario() {

		service.cadastrarUsuario(new Usuario(0L, "Amanda Ribeiro",
				"https://t.ctcdn.com.br/Uaa77l8Hm-2iSaypSQIPQwd4lic=/512x288/smart/i422804.png", "amanda@email.com.br",
				"123456789"));

		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(new Usuario(0L,
				"Amanda Ribeiro", "https://t.ctcdn.com.br/Uaa77l8Hm-2iSaypSQIPQwd4lic=/512x288/smart/i422804.png",
				"amanda@email.com.br", "123456789"));

		ResponseEntity<Usuario> resposta = testRestTemplate
				.exchange("/usuarios/cadastrar", HttpMethod.POST, requisicao, Usuario.class);

		assertEquals(HttpStatus.UNAUTHORIZED, resposta.getStatusCode());
	}

}
