package com.springreact.springBootReact.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springreact.springBootReact.model.entity.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}
