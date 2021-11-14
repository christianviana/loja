-- vendedores
INSERT INTO
public.vendedor (id, matricula, nome) 
VALUES(nextval('vendedor_id_seq'), 'LC', 'Luis Carlos Amaral');


INSERT INTO
public.vendedor (id, matricula, nome) 
VALUES(nextval('vendedor_id_seq'), 'RA', 'Roberto Almeida');

INSERT INTO
public.vendedor (id, matricula, nome) 
VALUES(nextval('vendedor_id_seq'), 'MM', 'Maria Maria');



-- produtos
INSERT INTO
public.produto (id, codigo, nome, preco) 
VALUES(nextval('produto_id_seq'), '1', 'produto1', '12.40');

INSERT INTO
public.produto (id, codigo, nome, preco) 
VALUES(nextval('produto_id_seq'), '2a', 'produto2', '11.40');

INSERT INTO
public.produto (id, codigo, nome, preco) 
VALUES(nextval('produto_id_seq'), '3bb', 'produto3', '2.50');

INSERT INTO
public.produto (id, codigo, nome, preco) 
VALUES(nextval('produto_id_seq'), '44', 'produto4', '1.46');



-- vendas
INSERT INTO
public.venda (numvenda, valortotal, vendedor_id) 
VALUES(nextval('venda_id_seq'), '100.01', 1);


INSERT INTO
public.venda (numvenda, valortotal, vendedor_id) 
VALUES(nextval('venda_id_seq'), '11.21', 2);

INSERT INTO
public.venda (numvenda, valortotal, vendedor_id) 
VALUES(nextval('venda_id_seq'), '111.21', 2);


INSERT INTO
public.venda (numvenda, valortotal, vendedor_id) 
VALUES(nextval('venda_id_seq'), '110.21', 3);


INSERT INTO
public.venda (numvenda, valortotal, vendedor_id) 
VALUES(nextval('venda_id_seq'), '34.21', 3);


INSERT INTO
public.venda (numvenda, valortotal, vendedor_id) 
VALUES(nextval('venda_id_seq'), '4.31', 3);























