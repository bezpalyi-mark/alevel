USE test_graph;

CREATE TABLE IF NOT EXISTS city(
  id   INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL UNIQUE,
PRIMARY KEY(id));

CREATE TABLE IF NOT EXISTS connection (cost INT UNSIGNED,
                                       from_city INT NOT NULL,
                                       to_city INT NOT NULL,
CONSTRAINT from_city_id FOREIGN KEY(from_city)REFERENCES city(id),
CONSTRAINT to_city_id FOREIGN KEY(to_city)REFERENCES city(id),
PRIMARY KEY(cost, from_city, to_city));

CREATE TABLE IF NOT EXISTS problems (id INT NOT NULL AUTO_INCREMENT,
                                     from_city INT NOT NULL,
                                     to_city INT NOT NULL,
CONSTRAINT start_city_id FOREIGN KEY(from_city)REFERENCES city(id),
CONSTRAINT finish_city_id FOREIGN KEY(to_city)REFERENCES city(id),
PRIMARY KEY(id));

CREATE TABLE IF NOT EXISTS found_routes (problem INT NOT NULL,
                                         min_cost INT NOT NULL,
CONSTRAINT problem_id FOREIGN KEY(problem)REFERENCES problems(id),
PRIMARY KEY(problem));

CREATE TABLE IF NOT EXISTS impossible_routes (problem INT NOT NULL,
CONSTRAINT impossible_problem_id FOREIGN KEY(problem)REFERENCES problems(id),
PRIMARY KEY(problem));