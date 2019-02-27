create schema if not exists flyway;
create schema if not exists backoffice;

create sequence backoffice.route_sequence start 1 increment 1;

create table backoffice.city (
    code char(3) not null primary key,
    name varchar(128)
);

create table backoffice.route (
    id integer not null primary key,
    departure_city char(3) not null,
    arrival_city char(3) not null,
    duration integer,
    foreign key (departure_city) references backoffice.city(code),
    foreign key (arrival_city) references backoffice.city(code)
);
