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
public.produto (codigo, nome, preco) 
VALUES(nextval('produto_id_seq'), 'produto1', '12.40');

INSERT INTO
public.produto (codigo, nome, preco) 
VALUES(nextval('produto_id_seq'), 'produto2', '11.40');

INSERT INTO
public.produto (codigo, nome, preco) 
VALUES(nextval('produto_id_seq'), 'produto3', '2.50');

INSERT INTO
public.produto (codigo, nome, preco) 
VALUES(nextval('produto_id_seq'), 'produto4', '1.46');

