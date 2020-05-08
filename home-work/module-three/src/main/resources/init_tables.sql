INSERT INTO user(id, email, phoneNumber, name, patronymic, surname)
VALUES (1, 'user1@gmail.com', '+380664346712', 'Andrii', 'Viktorovich', 'Hulyashkin'),
       (2, 'user2@gmail.com', '+380522546712', 'Dima', 'Leonidovich', 'Chebryakov'),
       (3, 'user3@gmail.com', '+380522593658', 'Leonid', 'Dmitrievich', 'Veselchakov');
INSERT INTO account(id, balance, user_id)
VALUES (1, 1020, 1),
       (2, 1000, 2),
       (3, 100000, 3),
       (4, 3500, 1),
       (5, 190000, 2);
INSERT INTO category(dtype, id, name)
VALUES ('Profit', 1, 'scholarship'),
       ('Profit', 2, 'side_job'),
       ('Profit', 3, 'bank_receipts'),
       ('Consumption', 4, 'shopping'),
       ('Consumption', 5, 'rent'),
       ('Consumption', 6, 'driveway');
INSERT INTO operation(id, instant, value, account_id)
VALUES (1, '2011-05-01 15:12:12', 1200, 1),
       (2, '2013-10-23 11:24:48', -2500, 5),
       (3, '2000-02-06 21:14:27', 8100, 3),
       (4, '2014-01-01 20:21:29', 1570, 4),
       (5, '2010-12-03 12:21:21', -10000, 2),
       (6, '2020-05-05 10:00:01', 2009, 2),
       (7,'2015-09-08 03:05:12', 20131, 3),
       (8, '2016-09-12 05:12:12', -1122, 4);
INSERT INTO operation_category(operation_id, categories_id, categories_name)
VALUES (1, 1, 'scholarship'),
       (2, 5, 'rent'),
       (3, 3, 'bank_receipts'),
       (4, 2, 'side_job'),
       (4, 1, 'scholarship'),
       (5, 4, 'shopping'),
       (6, 1, 'scholarship'),
       (7, 3, 'bank_receipts'),
       (8, 5, 'rent');