package com.springreact.springBootReact.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;


// Reconhece o JPA como mapeamento de uma entidade
@Entity 
//Definição e tabela
@Table (name = "usuario", schema =  "financas")
//@Setter // propriedade do Lombak que substitui o SET
//@Getter // propriedade do Lombak que substitui o GET
//@EqualsAndHashCode // propriedade do Lombak que substitui o HASHCODE
//@ToString // propriedade do Lombak que substitui o TOSTRING
//@NoArgsConstructor // construtor sem os argumentos
//@AllArgsConstructor // construtor com todas os argumentos
@Data // substitui todas as prorpiedades anteriores
@Builder
public class Usuario {

	//Colocar variavel como id
	@Id
	@Column(name = "id") // set name table 
	@GeneratedValue(strategy = GenerationType.IDENTITY) // set value generate automatic
	private Long id;
	
	@Column(name = "nome")	
	private String nome;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "senha")
	private String senha;

	
}
