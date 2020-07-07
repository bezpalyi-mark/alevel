CREATE DATABASE geronimo;
USE geronimo;

create table user
(
    id       bigint auto_increment
        primary key,
    active   bit          null,
    password varchar(255) null,
    username varchar(255) null
);

create table user_role
(
    user_id bigint       not null,
    role    varchar(255) null,
    constraint FK859n2jvi8ivhui0rl0esws6o
        foreign key (user_id) references user (id)
);

create table category
(
    id   bigint auto_increment
        primary key,
    name varchar(255) null
);

create table city
(
    id   bigint auto_increment
        primary key,
    name varchar(255) null
);

create table place
(
    id            bigint auto_increment
        primary key,
    is_crossroads bit          not null,
    name          varchar(255) null,
    rating        varchar(255) null,
    category_id   bigint       null,
    city_id       bigint       null,
    constraint FK3297dq7rblawjc9n4kx9htui4
        foreign key (category_id) references category (id),
    constraint FKd0im63f67sps1ty7956t9ahn4
        foreign key (city_id) references city (id)
);

create table road
(
    id      bigint auto_increment
        primary key,
    weight  double null,
    from_id bigint null,
    to_id   bigint null,
    constraint FKp2iol6adlq4vaae2720webjfn
        foreign key (to_id) references place (id),
    constraint FKqremke0f364o3ln63ewuqjstu
        foreign key (from_id) references place (id)
);

