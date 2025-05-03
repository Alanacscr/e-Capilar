-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;


insert into estado (sigla, nome) values ('TO', 'Tocantins');
insert into estado (sigla, nome) values ('SP', 'São Paulo');
insert into estado (sigla, nome) values ('GO', 'Goiás');
insert into estado (sigla, nome) values ('RS', 'Rio Grande do Sul');
insert into estado (sigla, nome) values ('RJ', 'Rio de Janeiro');

insert into municipio (nome, id_estado) values ('Palmas', 1);
insert into municipio (nome, id_estado) values ('Araguaina', 1);
insert into municipio (nome, id_estado) values ('Santos', 2);
insert into municipio (nome, id_estado) values ('Goiânia', 3);

insert into telefone (codigoarea, numero) values ('063', '99215-1290');
insert into telefone (codigoarea, numero) values ('063', '98129-1290');

insert into produto (nome, descricao, preco, quantidadeestoque, cor)
values ('Igora', 'Produto de otima qualidade', 79.90, 10, 1);

INSERT INTO usuario (nome, email, senha, perfil, id_telefone)
VALUES ('Alana', 'alana@gmail.com', '123', 1, 1);

insert into categoria (nome, id_produto) values ('fuleira', 1);

insert into endereco (logradouro, numero, bairro, cep, complemento, id_municipio)
values('Rua 1', 100,'Taquaralto', '11009-011','Predio', 1);


