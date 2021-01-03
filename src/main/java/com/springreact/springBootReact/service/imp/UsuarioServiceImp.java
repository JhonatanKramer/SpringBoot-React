package com.springreact.springBootReact.service.imp;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springreact.springBootReact.exception.ErroAutenticacao;
import com.springreact.springBootReact.exception.RegraNegocioException;
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
		Optional<Usuario> usuario =  repository.findByEmail(email);
		if (! usuario.isPresent()) {
			throw new ErroAutenticacao("Usuario não encontrado para o email informado");
		}
		
		if (!usuario.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Senha Inválida");
		}
		return usuario.get();
	}

	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		return repository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {
		boolean existe = repository.existsByEmail(email);
		if(existe) {
			throw new RegraNegocioException("Já existe um usuario cadastrado com este email."); 
		}
		
	}

	@Override
	public Optional<Usuario> obterPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
