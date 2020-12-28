package com.springreact.springBootReact.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springreact.springBootReact.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	// Não precisa criar a Query para busca, ele automaticamente pelo metodo findBy faz a busca, precisa estar com mesmo nome que dentro da classe nas propriedades
	// Busca por email
	Optional<Usuario> findByEmail(String email);
	Optional<Usuario> findByNome(String nome);
	// Busca por nome e email
	Optional<Usuario> findByNomeAndEmail(String nome, String email);
	//Verifica se um email existe
	boolean existByEmail(String email);
}
