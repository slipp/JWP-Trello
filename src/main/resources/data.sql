INSERT INTO user (id, user_id, password, email) VALUES (1, 'javajigi', 'test', 'javajigi@slipp.net');
INSERT INTO user (id, user_id, password, email) VALUES (2, 'sanjigi', 'test', 'sanjigi@slipp.net');

INSERT INTO role (id, role) VALUES (1, 'USER');
INSERT INTO role (id, role) VALUES (2, 'ADMIN');

INSERT INTO user_role(user_id, role_id) VALUES (1, 1);
INSERT INTO user_role(user_id, role_id) VALUES (1, 2);
INSERT INTO user_role(user_id, role_id) VALUES (2, 1);
