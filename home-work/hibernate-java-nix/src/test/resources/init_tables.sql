USE graph;

INSERT INTO city (name) VALUES ('gdansk'),
                               ('bydgoszcz'),
                               ('torun'),
                               ('warszawa');

INSERT INTO connection (cost, from_city, to_city) VALUES (1, 1, 2),
                                                         (3, 1, 3),
                                                         (1, 2, 1),
                                                         (1, 2, 3),
                                                         (4, 2, 4),
                                                         (3, 3, 1),
                                                         (1, 3, 2),
                                                         (1, 3, 4),
                                                         (4, 4, 2),
                                                         (1, 4, 3);

INSERT INTO problems (from_city, to_city) VALUES (1, 4),
                                                 (2, 4);