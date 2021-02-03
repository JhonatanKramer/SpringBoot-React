package com.springreact.springBootReact.service;

import java.util.List;
import java.util.Optional;

import com.springreact.springBootReact.model.entity.Lancamento;
import com.springreact.springBootReact.model.enums.StatusLancamento;

public interface LancamentoService {

	Lancamento salvar(Lancamento lancamento);

	Lancamento atualizar(Lancamento lancamento);

	void deletar(Lancamento lancamento);

	List<Lancamento> buscar(Lancamento lancamento);

	void atualizarStatus(Lancamento lancamento, StatusLancamento status);

	void validar(Lancamento lancamento);

	Optional<Lancamento> obterPorId(Long id);
}
