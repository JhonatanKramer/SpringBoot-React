package com.springreact.springBootReact.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.springreact.springBootReact.model.entity.Usuario;
import com.springreact.springBootReact.model.repository.UsuarioRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {
	
	@Autowired
	UsuarioService service;
	
	@Autowired
	UsuarioRepository repository;
	
	@Test 
	public void deveValidarEmail() {
		repository.deleteAll();
		service.validarEmail("email@email.com.br");
		
	}
	
	public void deveLancarErroAovalidarEmailQuandoExistirEmailCadastrado() {
		Usuario usuario = Usuario.builder().nome("nome").email("usuario@email.com").build();
		repository.save(usuario);
		service.validarEmail("usuario@email.com");
		
	}
	
}
