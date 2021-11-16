INSERT INTO tb_branch (name, state, road, street_Number, city, description) VALUES ('Filial Mossoró', 'Rio Grande do Norte', 'S/N', 25, 'Mossoró', 'Primeira filial');
INSERT INTO tb_branch (name, state, road, street_Number, city, description) VALUES ('Filial Natal', 'Rio Grande do Norte', 'S/N', 56, 'Natal', 'Segunda filial');
INSERT INTO tb_branch (name, state, road, street_Number, city, description) VALUES ('Filial Fortaleza', 'Ceará', 'S/N', 106, 'Fortaleza', 'Terceira filial');

INSERT INTO tb_manager (name, email, password, address, phone, permission) VALUES ('Pedro Santos', 'pedro@leon.com', 'pedro123', 'Mossoró', '84 999999999', 'Admistrador');
INSERT INTO tb_manager (name, email, password, address, phone, permission) VALUES ('Ana Ferreira', 'ana@leon.com', 'ana123', 'Mossoró', '84999999999', 'Gerente');
INSERT INTO tb_manager (name, email, password, address, phone, permission) VALUES ('José Lima', 'jose@leon.com', 'jose123', 'Mossoró', '84 999999999', 'Gerente');
INSERT INTO tb_manager (name, email, password, address, phone, permission) VALUES ('Maria Sousa', 'maria@leon.com', 'maria123', 'Mossoró', '84 999999999', 'Atendente');
INSERT INTO tb_manager (name, email, password, address, phone, permission) VALUES ('João Silva', 'joao@leon.com', 'joao123', 'Mossoró', '84 999999999', 'Atendente');
INSERT INTO tb_manager (name, email, password, address, phone, permission) VALUES ('Gabriel Santos', 'gabriel@leon.com', 'gabriel123', 'Mossoró', '84 999999999', 'Atendente');

-- INSERT INTO tb_branch_manager (branch_id, manager_id) VALUES (1, 1);
-- INSERT INTO tb_branch_manager (branch_id, manager_id) VALUES (1, 2);
-- INSERT INTO tb_branch_manager (branch_id, manager_id) VALUES (1, 5);
-- INSERT INTO tb_branch_manager (branch_id, manager_id) VALUES (2, 1);
-- INSERT INTO tb_branch_manager (branch_id, manager_id) VALUES (2, 3);
-- INSERT INTO tb_branch_manager (branch_id, manager_id) VALUES (2, 4);
INSERT INTO tb_branch_manager (branch_id, manager_id) VALUES (3, 2);
INSERT INTO tb_branch_manager (branch_id, manager_id) VALUES (3, 4);
INSERT INTO tb_branch_manager (branch_id, manager_id) VALUES (3, 5);

INSERT INTO tb_news (title, description, image_Url, date, manager_id) VALUES ('Noticia normal', 'Haverá um pequeno evento em breve', 'https://blogpilates.com.br/wp-content/uploads/2016/02/Studio-de-Pilates-CAPA.png', '2021-11-10', 3);
INSERT INTO tb_news (title, description, image_Url, date, manager_id) VALUES ('Noticia grande', '	Haverá um grande evento em breve. Haverá um grande evento em breve. Haverá um grande evento em breve.', 'https://blogpilates.com.br/wp-content/uploads/2016/02/Studio-de-Pilates-CAPA.png', '2021-11-25', 4);
INSERT INTO tb_news (title, description, image_Url, date, manager_id) VALUES ('Noticia normal', '	Haverá um grande evento em breve.', 'https://blogpilates.com.br/wp-content/uploads/2016/02/Studio-de-Pilates-CAPA.png', '2021-10-15', 3);


