package com.springreact.springBootReact.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springreact.springBootReact.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
