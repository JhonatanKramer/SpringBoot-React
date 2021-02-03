DROP TABLE financas.usuario

CREATE TABLE financas.usuario(
id bigserial NOT NULL PRIMARY KEY,
nome CHARACTER VARYING (150),
email CHARACTER VARYING (100),
senha CHARACTER VARYING (20),
data_cadatro DATE DEFAULT NOW()
);

CREATE TABLE financas.lancamento(
id bigserial NOT NULL PRIMARY KEY,
descricao CHARACTER VARYING (150) NOT NULL,
mes INTEGER NOT NULL,
ano INTEGER NOT NULL,
valor NUMERIC(16,2) NOT NULL,
tipo CHARACTER VARYING (20) CHECK (tipo IN ('RECEITA','DESPESA')) NOT NULL , -- Toda vez que for inserido um valor, verifica se esta no check
status CHARACTER VARYING (20) CHECK (status IN ('PENDENTE', 'CANCELADO', 'EFETIVADO')) NOT NULL,
id_usuario BIGINT REFERENCES financas.usuario (id) NOT NULL,
data_cadastro date DEFAULT NOW()
);