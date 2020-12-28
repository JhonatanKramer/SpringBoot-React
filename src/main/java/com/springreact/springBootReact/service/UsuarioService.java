package com.springreact.springBootReact.service;

import com.springreact.springBootReact.model.entity.Usuario;

//Interface de metodos para classe usuario
public interface UsuarioService {

	//metodo de para autenticar usuario
	Usuario autenticar(String email, String senha);
	// metodo para salvar usuario
	Usuario salvarUsuario(Usuario usuario);
	
	void validarEmail(String email);
	
	
}
