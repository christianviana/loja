-- vendedores
INSERT INTO
public.vendedor (matricula, nome) 
VALUES(nextval('vendedor_id_seq'), 'Luis Carlos Amaral');


INSERT INTO
public.vendedor (matricula, nome) 
VALUES(nextval('vendedor_id_seq'), 'Roberto Almeida');

INSERT INTO
public.vendedor (matricula, nome) 
VALUES(nextval('vendedor_id_seq'), 'Maria Maria');



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



-- vendas
INSERT INTO
public.venda (numvenda, valortotal, vendedor_matricula) 
VALUES(nextval('venda_id_seq'), '100.01', 1);


INSERT INTO
public.venda (numvenda, valortotal, vendedor_matricula) 
VALUES(nextval('venda_id_seq'), '11.21', 2);

INSERT INTO
public.venda (numvenda, valortotal, vendedor_matricula) 
VALUES(nextval('venda_id_seq'), '111.21', 2);


INSERT INTO
public.venda (numvenda, valortotal, vendedor_matricula) 
VALUES(nextval('venda_id_seq'), '110.21', 3);


INSERT INTO
public.venda (numvenda, valortotal, vendedor_matricula) 
VALUES(nextval('venda_id_seq'), '34.21', 3);


INSERT INTO
public.venda (numvenda, valortotal, vendedor_matricula) 
VALUES(nextval('venda_id_seq'), '4.31', 3);



INSERT INTO
public.item (id, codigoproduto, descricao, quantidade, valorunitario, produto_codigo, venda_numvenda) 
VALUES(nextval('item_id_seq'), 1, 'o produto1', 1, '4.31', 1, 1);


INSERT INTO
public.item (id, codigoproduto, descricao, quantidade, valorunitario, produto_codigo, venda_numvenda) 
VALUES(nextval('item_id_seq'), 1, 'o produto1', 2, '5.31', 1, 2);


INSERT INTO
public.item (id, codigoproduto, descricao, quantidade, valorunitario, produto_codigo, venda_numvenda) 
VALUES(nextval('item_id_seq'), 1, 'o produto3', 1, '5.31', 2, 3);


INSERT INTO
public.item (id, codigoproduto, descricao, quantidade, valorunitario, produto_codigo, venda_numvenda) 
VALUES(nextval('item_id_seq'), 1, 'o produto3', 4, '5.31', 3, 4);


















