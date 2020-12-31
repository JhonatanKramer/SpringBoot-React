package com.springreact.springBootReact.service;

import java.util.Optional;

import com.springreact.springBootReact.model.entity.Usuario;



public interface UsuarioService {

	Usuario autenticar(String email, String senha);
	
	Usuario salvarUsuario(Usuario usuario);
	
	public void validarEmail(String email);
	
	Optional<Usuario> obterPorId(Long id);
	
}
