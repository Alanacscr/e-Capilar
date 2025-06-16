-- Insere Estados
insert into estado (sigla, nome) values ('TO', 'Tocantins');
insert into estado (sigla, nome) values ('SP', 'São Paulo');
insert into estado (sigla, nome) values ('GO', 'Goiás');
insert into estado (sigla, nome) values ('RS', 'Rio Grande do Sul');
insert into estado (sigla, nome) values ('RJ', 'Rio de Janeiro');

-- Insere Municipios
insert into municipio (nome, id_estado) values ('Palmas', 1);
insert into municipio (nome, id_estado) values ('Araguaina', 1);
insert into municipio (nome, id_estado) values ('Santos', 2);
insert into municipio (nome, id_estado) values ('Goiânia', 3);

-- Insere Telefones
insert into telefone (codigoarea, numero) values ('063', '99215-1290');
insert into telefone (codigoarea, numero) values ('063', '98129-1290');
insert into telefone (codigoarea, numero) values ('061', '92133-0000');

-- Insere Produtos
insert into produto (nome, descricao, preco, quantidadeestoque, cor)
values ('Igora', 'Produto de otima qualidade', 50, 10, 1);

insert into produto (nome, descricao, preco, quantidadeestoque, cor)
values ('L´oreal', 'Cabelos lisos', 55, 100, 2);

insert into produto (nome, descricao, preco, quantidadeestoque, cor)
values ('Clear', 'Cabelo Masculino', 20, 10, 3);

-- Insere Categorias
insert into categoria (nome, id_produto) values ('Promoção', 1);
insert into categoria (nome, id_produto) values ('Lançamentos', 2);
insert into categoria (nome, id_produto) values ('Premium', 2);

-- Insere Endereços
insert into endereco (logradouro, numero, bairro, cep, complemento, id_municipio)
values('Rua 1', 100,'Taquaralto', '11009-011','Predio', 1);

insert into endereco (logradouro, numero, bairro, cep, complemento, id_municipio)
values('Rua 2', 10,'Plano diretor Norte', '13229-011','Casa', 1);

insert into endereco (logradouro, numero, bairro, cep, complemento, id_municipio)
values('Planalto', 10,'Plano diretor Sul', '91223-011','Apartamento', 1);

-- Insere Usuários
-- Usuario Administrador
insert into usuario(nome, email, senha, perfil, id_telefone, id_endereco) 
    values (
        'Alana', 
        'alana@gmail.com',
        'SiM9w9cv/QHp+fZSykTmN52bUoj++hlYrZoet0hxU8eajwrdo6L5hmWoOm96rYeFQ1YyMKBKLuRE05aC5FKL/Q==',
        1,
        2,
        1);

-- Usuario Cliente
insert into usuario(nome, email, senha, perfil, id_telefone, id_endereco) 
values (
        'Janio', 
        'janio@gmail.com',
        'SiM9w9cv/QHp+fZSykTmN52bUoj++hlYrZoet0hxU8eajwrdo6L5hmWoOm96rYeFQ1YyMKBKLuRE05aC5FKL/Q==',
        2,
        1,
        2);

-- Insere Pagamentos
insert into pagamento (tipopagamento, valor)
values('Pix', 50);

insert into pagamento (tipopagamento, valor)
values('Pix', 50);

-- Insere meio de pagamento por boleto
insert into boleto (id, datavencimento, codigobarras)
values(1, '2022-03-10T12:15:50', '126gfg2fg5gf36t273hudhfudjfjfk');

-- Insere meio de pagamento por pix
insert into pix (id, chavepix) 
values (2, '123456789');

-- Insere Pedidos
insert into pedido (datahora, id_usuario, totalpedido, id_pagamento) 
values ('2025-05-10T12:15:50', 1, 50, 1);

insert into itempedido (preco, quantidade, id_produto, id_pedido) 
values (50, 1, 1, 1);









