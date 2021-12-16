-- cleaning tables

DELETE FROM goodmeal.recipes_rating;
DELETE FROM goodmeal.users_roles;
DELETE FROM goodmeal.roles;
DELETE FROM goodmeal.users;

-- setting up test users

INSERT INTO goodmeal.users (id, login, password, name, surname, email, bday)
VALUES (0, 'user0', '1234', 'name0', 'surname0', 'email0@email.com', '2000-01-01');

INSERT INTO goodmeal.users (id, login, password, name, surname, email, bday)
VALUES (1, 'user1', '1234', 'name1', 'surname1', 'email1@email.com', '2000-01-01');

INSERT INTO goodmeal.users (id, login, password, name, surname, email, bday)
VALUES (2, 'user2', '1234', 'name2', 'surname2', 'email2@email.com', '2000-01-01');

-- setting up test roles

INSERT INTO goodmeal.roles (id, role)
VALUES (0, 'USER');

INSERT INTO goodmeal.roles (id, role)
VALUES (1, 'ADMIN');

-- setting roles to users

INSERT INTO goodmeal.users_roles (user_id,role_id)
VALUES (0,0);

INSERT INTO goodmeal.users_roles (user_id,role_id)
VALUES (1,0);

INSERT INTO goodmeal.users_roles (user_id,role_id)
VALUES (2,1);