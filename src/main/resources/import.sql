INSERT INTO authority(name) values ('READ');
INSERT INTO authority(name) values ('WRITE');
INSERT INTO authority(name) values ('UPDATE');
INSERT INTO authority(name) values ('DELETE');

INSERT INTO role(name) values ('ADMIN');
INSERT INTO role(name) values ('USER');


INSERT INTO role_authorities(role_id, authority_id) values (1, 1);
INSERT INTO role_authorities(role_id, authority_id) values (1, 2);
INSERT INTO role_authorities(role_id, authority_id) values (1, 3);
INSERT INTO role_authorities(role_id, authority_id) values (1, 4);
INSERT INTO role_authorities(role_id, authority_id) values (2, 1);
INSERT INTO role_authorities(role_id, authority_id) values (2, 2);
INSERT INTO role_authorities(role_id, authority_id) values (2, 3);
INSERT INTO role_authorities(role_id, authority_id) values (2, 4);


INSERT INTO users(name, password, role_id) values ('admin', 'admin', 1);
INSERT INTO users(name, password, role_id) values ('user', 'user', 2);
