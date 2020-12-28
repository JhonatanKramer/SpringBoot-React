package com.springreact.springBootReact.service.imp;

import org.springframework.stereotype.Service;

import com.springreact.springBootReact.model.entity.Usuario;
import com.springreact.springBootReact.model.repository.UsuarioRepository;
import com.springreact.springBootReact.service.UsuarioService;

@Service
public class UsuarioServiceImp implements UsuarioService {

	private UsuarioRepository repository;
	
	public UsuarioServiceImp(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario salvarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validarEmail(String email) {
		// TODO Auto-generated method stub
		
	}

}
