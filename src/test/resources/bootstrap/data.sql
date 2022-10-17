ALTER TABLE country ALTER COLUMN id RESTART WITH 1;
ALTER TABLE region ALTER COLUMN id RESTART WITH 1;
ALTER TABLE location ALTER COLUMN id RESTART WITH 1;
ALTER TABLE sport ALTER COLUMN id RESTART WITH 1;
ALTER TABLE trip ALTER COLUMN id RESTART WITH 1;


INSERT INTO country (name)
VALUES ('Russia');
INSERT INTO country (name)
VALUES ('Moldova');

INSERT INTO region (name, country_id)
VALUES ('Chisinau', 2);
INSERT INTO region (name, country_id)
VALUES ('Moscow', 1);

INSERT INTO location (name, region_id, country_id)
VALUES ('Falesti', 1, 2);
INSERT INTO location (name, region_id, country_id)
VALUES ('Chisinau', 1, 2);
INSERT INTO location (name, region_id, country_id)
VALUES ('Moscow', 2, 1);
INSERT INTO location (name, region_id, country_id)
VALUES ('Himki', 2, 1);

INSERT INTO sport (name)
VALUES ('Scuba Diving');
INSERT INTO sport (name)
VALUES ('Paragliding');
INSERT INTO sport (name)
VALUES ('Skateboarding');

INSERT INTO trip(location_id, sport_id, price, start_date, end_date)
VALUES (1, 1, 350, '2020-01-01', '2020-01-10');
INSERT INTO trip(location_id, sport_id, price, start_date, end_date)
VALUES (1, 2, 600, '2020-05-07', '2020-05-30');
INSERT INTO trip(location_id, sport_id, price, start_date, end_date)
VALUES (2, 2, 580, '2020-12-17', '2021-01-15');

INSERT INTO country (name) values ('CountryTest1');

INSERT INTO region (id, name, country_id)
values (7, 'RegionsTest1', 3);
INSERT INTO region (id, name, country_id)
values (8, 'RegionsTest2', 3);

INSERT INTO location (id, name, region_id, country_id)
VALUES (10, 'LocationTest1', 7, 3);
INSERT INTO location (name, region_id, country_id)
VALUES ('LocationTest2', 7, 3);
INSERT INTO location (name, region_id, country_id)
VALUES ('LocationTest3', 7, 3);

insert into trip (location_id, sport_id, price, start_date, end_date)
VALUES (10, 2, 809, '2020-01-12', '2020-02-01');
insert into trip (location_id, sport_id, price, start_date, end_date)
VALUES (10, 1, 708, '2020-05-05', '2020-05-15');

INSERT INTO country (name) values ('CountryTest2');



