package com.springreact.springBootReact.api.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.springreact.springBootReact.api.dto.UsuarioDTO;
import com.springreact.springBootReact.exception.ErroAutenticacao;
import com.springreact.springBootReact.exception.RegraNegocioException;
import com.springreact.springBootReact.model.entity.Usuario;
import com.springreact.springBootReact.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios") // todas requisições /api/usuario entra nesta classe
@RequiredArgsConstructor
public class UsuarioResource {

	// Quando acessar a URL (/) chama o metodo.
//	@GetMapping("/")
//	public String HelloWord() {
//		 return "Hello Word!";
//	}

	private final UsuarioService service;

	@PostMapping("/autenticar")
	public ResponseEntity autenticar(@RequestBody UsuarioDTO dto) {
		try {
			Usuario usuarioAutenticado = service.autenticar(dto.getEmail(), dto.getSenha());
			return ResponseEntity.ok(usuarioAutenticado);
		} catch (ErroAutenticacao e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	// RequestBody exige que os dados dos usuario venha com essa propriedades
	@PostMapping
	public ResponseEntity salvar(@RequestBody UsuarioDTO dto) {

		Usuario usuario = Usuario.builder().nome(dto.getNome()).email(dto.getEmail()).senha(dto.getSenha()).build();

		try {
			Usuario usuarioSalvo = service.salvarUsuario(usuario);
			return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

}
