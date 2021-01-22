package com.springreact.springBootReact.service.imp;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import com.springreact.springBootReact.exception.RegraNegocioException;
import com.springreact.springBootReact.model.entity.Lancamento;
import com.springreact.springBootReact.model.enums.StatusLancamento;
import com.springreact.springBootReact.model.repository.LancamentoRepository;
import com.springreact.springBootReact.service.LancamentoService;

@Service
public class LancamentoServiceImp implements LancamentoService {

	LancamentoRepository lancamentoRepository;

	public LancamentoServiceImp(LancamentoRepository lancamentoRepository) {
		this.lancamentoRepository = lancamentoRepository;
	}

	@Override
	@Transactional
	// Transactiona efetua um begin e comita em caso de sucesso ou rollback em caso
	// de erro
	public Lancamento salvar(Lancamento lancamento) {
		validar(lancamento);
		lancamento.setStatus(StatusLancamento.PENDENTE);
		return lancamentoRepository.save(lancamento);
	}

	@Override
	@Transactional
	public Lancamento atualizar(Lancamento lancamento) {
		// Verificar se o objeto e não null
		Objects.requireNonNull(lancamento.getId());
		validar(lancamento);
		// Metodo Save Salva se se não tem Id e atualiza se tiver Id.
		return lancamentoRepository.save(lancamento);
	}

	@Override
	@Transactional
	public void deletar(Lancamento lancamento) {
		Objects.requireNonNull(lancamento.getId());
		lancamentoRepository.delete(lancamento);
	}

	@Override
	@Transactional(readOnly = true) // transacao apenas de leitura
	public List<Lancamento> buscar(Lancamento lancamento) {
		/* Cria uma especie do objeto somente com os campo informados.
		 *o example possui diversos metodos dentro do ExampleMatcher 
		 *.matching().withIgnoreCase() faz a busca com ignoreCase
		 *.withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING) procura nos campos string igual um like.
		 *StringMatcher.ENDING = e procurar as string que termina com o que ta na descrição
		 *StringMatcher.STARTING procura no começo
		 *StringMatcher.EXACT e procura exato
		 */
		Example lancamentoExemplo = Example.of(lancamento, ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		// busca tudo que que conter o que tem no lancamentoExemplo
		return lancamentoRepository.findAll(lancamentoExemplo);
	}

	@Override
	public void atualizarStatus(Lancamento lancamento, StatusLancamento status) {
		lancamento.setStatus(status);
		atualizar(lancamento);
	}

	@Override
	public void validar(Lancamento lancamento) {
		// trim é um metodo que remove os espaços
		if (lancamento.getDescricao() == null || lancamento.getDescricao().trim().equals("")) {
			throw new RegraNegocioException("Informe uma descrição valida"); 
		}
		
		if (lancamento.getMes() == null || lancamento.getMes() < 1 || lancamento.getMes() > 12 ) {
			throw new RegraNegocioException("Informe uma mês valido."); 
		}
		
		if (lancamento.getAno() == null || lancamento.getAno().toString().length()!=4) {
			throw new RegraNegocioException("Informe uma ano valido."); 
		}
		
		if (lancamento.getUsuario() == null || lancamento.getUsuario().getId() == null) {
			throw new RegraNegocioException("Informe um usuario."); 
		}
		
		if (lancamento.getValor()==null || lancamento.getValor().compareTo(BigDecimal.ZERO) <1) {
			throw new RegraNegocioException("Informe um valor Valido.");
		}
		
		if (lancamento.getTipo() == null) {
			throw new RegraNegocioException("Informe um tipo Valido.");
		}
	}

}
