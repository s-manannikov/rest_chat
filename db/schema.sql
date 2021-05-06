create table role (
    id serial primary key,
    name varchar(100)
);

create table person (
    id serial primary key,
    username varchar(100),
    password varchar(100),
    role_id int references role(id)
);

create table room (
    id serial primary key,
    name varchar(100)
);

create table message (
    id serial primary key,
    text varchar(2000),
    person_id int references person(id),
    room_id int references room(id),
    created timestamp without time zone not null default now()
);

create table room_person (
    id serial primary key,
    room_id int references room(id),
    person_id int references person(id)
);

insert into role (name) values ('ROLE_USER');
insert into role (name) values ('ROLE_ADMIN');