INSERT INTO user(id, email, phoneNumber, name, patronymic, surname)
VALUES (1, 'user1@gmail.com', '+380994776352', 'AAA', 'CCC', 'BBB'),
       (3, 'user3@gmail.com', '+380932847593', 'Nick', 'Igorevich', 'Petrovich');

INSERT INTO account(id, balance, user_id)
VALUES (1, 20000, 1),
       (3, 1500, 3);

INSERT INTO category(dtype, id, name)
VALUES ('Profit', 1, 'salary'),
       ('Consumption', 2, 'shopping');

INSERT INTO operation(id, instant, value, account_id)
VALUES (1, '2013-10-23 11:24:48', 40000, 1),
       (2, '2014-11-30 21:41:04', -1500, 1);

INSERT INTO operation_category(operation_id, categories_id, categories_name)
VALUES (1, 1, 'salary'),
       (2, 2, 'shopping');