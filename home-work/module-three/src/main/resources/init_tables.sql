INSERT INTO user(id, email, phoneNumber, name, patronymic, surname)
VALUES (1, 'user#1@gmail.com', '+380664346712', 'Andrii', 'Viktorovich', 'Hulyashkin');
INSERT INTO account(id, balance, user_id)
VALUES (1, 10000, 1),
       (2, 1000, 1),
       (3, 100000, 1),
       (4, 3500, 1),
       (5, 190000, 1);
INSERT INTO category(dtype, id, name)
VALUES ('Profit', 1, 'salary'),
       ('Profit', 2, 'sell'),
       ('Profit', 3, 'transferIN'),
       ('Consumption', 4, 'penalty'),
       ('Consumption', 5, 'buy'),
       ('Consumption', 6, 'transferOUT');
INSERT INTO operation(id, instant, value, account_id)
VALUES (1, '2010-03-01 13:56:01', 1000, 1),
       (2, '2002-12-10 08:34:44', -500, 2),
       (3, '2018-06-06 23:44:17', 800, 3),
       (4, '2019-01-01 20:20:20', 12500, 4),
       (5, '2020-02-03 19:21:01', -10000, 5),
       (6, '2020-04-05 06:34:51', 8000, 1),
       (7, '2020-05-05 00:00:01', 199999, 2),
       (8, NOW(), -99999, 3),
       (9, NOW(), 12, 4);
INSERT INTO operation_category(operation_id, categories_id, categories_name)
VALUES (1, 1, 'salary'),
       (2, 5, 'buy'),
       (3, 3, 'transferIN'),
       (4, 2, 'sell'),
       (5, 4, 'penalty'),
       (6, 1, 'salary'),
       (7, 3, 'transferIN'),
       (8, 5, 'buy'),
       (9, 1, 'salary');