insert into "tb_produto" values(10001,'Calca','../../assets/imagens/produto.jpg', 50.2);
insert into "tb_produto" values(10002,'Tenis', '/assets/imagens/produto2.jpg', 145.65);
insert into "tb_produto" values(10003,'Camisa', '../../assets/imagens/produto3.jpg', 29.99);
insert into "tb_produto" values(10004,'Camisa', '/assets/imagens/produto3.jpg', 29.99);
-- UPDATE hibernate_sequence SET next_val=10003 where next_val=10002;

 INSERT INTO "tb_cliente" (ct_email, ct_telefone, nm_cliente, ct_endereco, dp_cpf) VALUES ('admin@admin.com','(66) 3605-0246','Enrico Ricardo da Costa','Rua Doutor João Gonçalves','169.482.080-70');
 INSERT INTO "tb_cliente" (ct_email, ct_telefone, nm_cliente, ct_endereco, dp_cpf) VALUES ('john@gmail.com','(92) 98816-0117','Ana Sarah da Mota','Rua Rio de Janeiro','551.422.360-41');
 INSERT INTO "tb_cliente" (ct_email, ct_telefone, nm_cliente, ct_endereco, dp_cpf) VALUES ('sham@yahoo.com','(91) 99570-6892','Marcelo Gael Novaes','Travessa Liberdade','201.462.720-72');

INSERT INTO "tb_carrinho_compras" (ultima_modificacao, id_cliente_no_carrinho) VALUES (now(), 1);
INSERT INTO "tb_carrinho_compras" (ultima_modificacao, id_cliente_no_carrinho) VALUES (now(), 2);
INSERT INTO "tb_carrinho_compras" (ultima_modificacao, id_cliente_no_carrinho) VALUES (now(), 3);

INSERT INTO "tb_carrinho_itens" (quantidade, id_cliente_no_carrinho) VALUES (2, 10001);
INSERT INTO "tb_carrinho_itens" (quantidade, id_cliente_no_carrinho) VALUES (3, 10002);
INSERT INTO "tb_carrinho_itens" (quantidade, id_cliente_no_carrinho) VALUES (4, 10003);


INSERT INTO "items_carrinho" (carrinho_id, item_carrinho_id) VALUES (1, 3);
INSERT INTO "items_carrinho" (carrinho_id, item_carrinho_id) VALUES (2, 2);
INSERT INTO "items_carrinho" (carrinho_id, item_carrinho_id) VALUES (3, 1);