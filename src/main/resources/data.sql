INSERT INTO tb_branch (name, state, road, street_Number, city, description) VALUES ('Filial Mossoró', 'Rio Grande do Norte', 'S/N', 25, 'Mossoró', 'Primeira filial');
INSERT INTO tb_branch (name, state, road, street_Number, city, description) VALUES ('Filial Natal', 'Rio Grande do Norte', 'S/N', 56, 'Natal', 'Segunda filial');
INSERT INTO tb_branch (name, state, road, street_Number, city, description) VALUES ('Filial Fortaleza', 'Ceará', 'S/N', 106, 'Fortaleza', 'Terceira filial');

INSERT INTO tb_authentication (email, password) VALUES ('admin@leon.com', '$2a$10$U3PcClPD9Hemc63N5DSfLOfGcS2NkCy.sccGEDsT4IHI0AUTZJrw6');
INSERT INTO tb_authentication (email, password) VALUES ('manager@leon.com', '$2a$10$U3PcClPD9Hemc63N5DSfLOfGcS2NkCy.sccGEDsT4IHI0AUTZJrw6');
INSERT INTO tb_authentication (email, password) VALUES ('user@leon.com', '$2a$10$U3PcClPD9Hemc63N5DSfLOfGcS2NkCy.sccGEDsT4IHI0AUTZJrw6');

INSERT INTO tb_manager (authentication_id, name, address, phone) VALUES (1, 'Matheus', 'Mossoró', '84 999999999');
INSERT INTO tb_manager (authentication_id, name, address, phone) VALUES (2, 'Pedro Santos', 'Mossoró', '84 999999999');

INSERT INTO tb_user (authentication_id, name, active, restitutions) VALUES (3, 'José', true, 10);

INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');
INSERT INTO tb_role (authority) VALUES ('ROLE_MANAGER');
INSERT INTO tb_role (authority) VALUES ('ROLE_CLERK');
INSERT INTO tb_role (authority) VALUES ('ROLE_USER');

INSERT INTO tb_authentication_role (authentication_id, role_id) VALUES (1, 1);
INSERT INTO tb_authentication_role (authentication_id, role_id) VALUES (2, 2);
INSERT INTO tb_authentication_role (authentication_id, role_id) VALUES (3, 4);

INSERT INTO tb_branch_manager (branch_id, manager_id) VALUES (3, 1);
INSERT INTO tb_branch_manager (branch_id, manager_id) VALUES (3, 1);
INSERT INTO tb_branch_manager (branch_id, manager_id) VALUES (3, 2);

INSERT INTO tb_news (title, description, image_Url, date, manager_id) VALUES ('Noticia normal', 'Haverá um pequeno evento em breve', 'https://blogpilates.com.br/wp-content/uploads/2016/02/Studio-de-Pilates-CAPA.png', '2021-11-10', 1);
INSERT INTO tb_news (title, description, image_Url, date, manager_id) VALUES ('Noticia grande', '	Haverá um grande evento em breve. Haverá um grande evento em breve. Haverá um grande evento em breve.', 'https://blogpilates.com.br/wp-content/uploads/2016/02/Studio-de-Pilates-CAPA.png', '2021-11-25', 1);
INSERT INTO tb_news (title, description, image_Url, date, manager_id) VALUES ('Noticia normal', '	Haverá um grande evento em breve.', 'https://blogpilates.com.br/wp-content/uploads/2016/02/Studio-de-Pilates-CAPA.png', '2021-10-15', 2);


