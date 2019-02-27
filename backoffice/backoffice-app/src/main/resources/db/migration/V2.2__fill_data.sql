insert into backoffice.city (code, name) values ('MAD', 'Madrid');
insert into backoffice.city (code, name) values ('BCN', 'Barcelona');
insert into backoffice.city (code, name) values ('VLC', 'Valencia');
insert into backoffice.city (code, name) values ('MIL', 'Milan');
insert into backoffice.city (code, name) values ('PAR', 'Paris');
insert into backoffice.city (code, name) values ('LON', 'London');

insert into backoffice.route (id, departure_city, arrival_city, duration) values (nextval('backoffice.route_sequence'), 'PAR', 'MIL', 400);
insert into backoffice.route (id, departure_city, arrival_city, duration) values (nextval('backoffice.route_sequence'), 'BCN', 'VLC', 180);
insert into backoffice.route (id, departure_city, arrival_city, duration) values (nextval('backoffice.route_sequence'), 'MAD', 'VLC', 250);
insert into backoffice.route (id, departure_city, arrival_city, duration) values (nextval('backoffice.route_sequence'), 'MAD', 'BCN', 150);
insert into backoffice.route (id, departure_city, arrival_city, duration) values (nextval('backoffice.route_sequence'), 'BCN', 'PAR', 200);
insert into backoffice.route (id, departure_city, arrival_city, duration) values (nextval('backoffice.route_sequence'), 'VLC', 'PAR', 500);
insert into backoffice.route (id, departure_city, arrival_city, duration) values (nextval('backoffice.route_sequence'), 'BCN', 'MIL', 1000);
insert into backoffice.route (id, departure_city, arrival_city, duration) values (nextval('backoffice.route_sequence'), 'VLC', 'MIL', 450);
insert into backoffice.route (id, departure_city, arrival_city, duration) values (nextval('backoffice.route_sequence'), 'VLC', 'LON', 2450);
insert into backoffice.route (id, departure_city, arrival_city, duration) values (nextval('backoffice.route_sequence'), 'PAR', 'LON', 350);
