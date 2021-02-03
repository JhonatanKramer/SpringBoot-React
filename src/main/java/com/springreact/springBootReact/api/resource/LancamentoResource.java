package com.springreact.springBootReact.api.resource;

import java.security.Provider.Service;
import java.util.List;
import java.util.Optional;

import org.hibernate.validator.constraints.Currency;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springreact.springBootReact.api.dto.LancamentoDTO;
import com.springreact.springBootReact.exception.RegraNegocioException;
import com.springreact.springBootReact.model.entity.Lancamento;
import com.springreact.springBootReact.model.entity.Usuario;
import com.springreact.springBootReact.model.enums.StatusLancamento;
import com.springreact.springBootReact.model.enums.TipoLancamento;
import com.springreact.springBootReact.service.LancamentoService;
import com.springreact.springBootReact.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/lancamentos")
@RequiredArgsConstructor // inicia automatico todos os argumentos que termina com final
public class LancamentoResource {

	private final LancamentoService lancamentoService;
	private final UsuarioService usuarioService;

	
	// Criar um recurso novo no servidor
	@PostMapping
	public ResponseEntity salvar(@RequestBody LancamentoDTO dto) {

		try {
			Lancamento lancamento = converter(dto);
			lancamento = lancamentoService.salvar(lancamento);

			return new ResponseEntity(lancamento, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	
	/* @RequestParam busca pelos parametros, 
	 * value = valor do parametro
	 * required = false significa que não é obrigatorio informar.
	 * caso não tenha essas propriedades o parametro se torna obrigatorio
	 */ 
	@GetMapping
	public ResponseEntity buscar( @RequestParam (value = "descricao", required = false) String descricao, 
								  @RequestParam (value ="mes", required = false) Integer mes,
								  @RequestParam (value ="ano", required = false) Integer ano,
								  @RequestParam ("idUsuario") Long idUsuario) {
		Lancamento lancamento = new Lancamento();
		lancamento.setDescricao(descricao);
		lancamento.setMes(mes);
		lancamento.setAno(ano);
		
		Optional<Usuario>usuario = usuarioService.obterPorId(idUsuario);
		
		if (!usuario.isPresent()) {
			return ResponseEntity.badRequest().body("Usuario não encontrado para o id"); 
		}else {
			lancamento.setUsuario(usuario.get());
		}
		
		List<Lancamento>lancamentos = lancamentoService.buscar(lancamento);
		return ResponseEntity.ok(lancamentos); 		
	}
	
	// putMapping é para atualizar um recurso no servidor
	// Precisa passar id da entidade, e informar no metodo qual é
	@PutMapping ("{id}")
	public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody LancamentoDTO dto) {
		// obtem o lancamento por id e se encontrar faz
		return lancamentoService.obterPorId(id).map(entity -> {
			try {
				Lancamento lancamento = converter(dto);
				lancamento.setId(entity.getId());
				lancamentoService.atualizar(lancamento);
				return ResponseEntity.ok(lancamento);
			} catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage()); 
			}
			
		}).orElseGet(() -> new ResponseEntity("Lancamento não encontrado na base de dados", HttpStatus.BAD_REQUEST)); 	
						 
	}
	
	// @DeleteMapping é para deletar um recurso na base
		// Precisa passar id da entidade, e informar no metodo qual é
		@DeleteMapping ("{id}")
		public ResponseEntity deletar (@PathVariable("id") Long id) {
			return lancamentoService.obterPorId(id).map( entity -> {
				lancamentoService.deletar(entity);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}).orElseGet(() -> new ResponseEntity("Lancamento não encontrado na base", HttpStatus.BAD_REQUEST));
					 
		}

	private Lancamento converter(LancamentoDTO dto) {
		Lancamento lancamento = new Lancamento();
		lancamento.setId(dto.getId());
		lancamento.setDescricao(dto.getDescricao());
		lancamento.setAno(dto.getAno());
		lancamento.setMes(dto.getMes());
		lancamento.setValor(dto.getValor());
		// buscar usuario e .orElseThrow caso de errado lanca uma exeção
		Usuario usuario = usuarioService.obterPorId(dto.getIdUsuario())
				.orElseThrow(() -> new RegraNegocioException("Usuario não encontrado para o id"));

		lancamento.setUsuario(usuario);
		
		if (dto.getTipo() != null) {
			lancamento.setTipo(TipoLancamento.valueOf(dto.getTipo()));
		}
	
		
		if (dto.getStatus() != null) {
			lancamento.setStatus(StatusLancamento.valueOf(dto.getStatus()));
		}
		

		return lancamento;
	}
	
	
}
