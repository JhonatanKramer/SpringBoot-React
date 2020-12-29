package com.springreact.springBootReact.model.repository;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

 
import com.springreact.springBootReact.model.entity.Usuario;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UsuarioRepositoryTest  {

  @Autowired
  UsuarioRepository repository;
  
  @Test
  public void deveVerificarAExistenciaDeUmEmail() {
     //Cenario
  Usuario usuario = Usuario.builder().nome("usuario").email("usuario@email.com").build();
     repository.save(usuario);
     
     //AcAO / execucao
      boolean result = repository.existsByEmail("usuario@email.com");
     
     //Verificacao
      
      Assertions.assertThat(result).isTrue();
    
	}

}