INSERT INTO country (name) value ('CountryTest1');

INSERT INTO region (id, name, country_id)
values (7, 'RegionsTest1', 3);
INSERT INTO region (id, name, country_id)
values (8, 'RegionsTest2', 3);

INSERT INTO location (id, name, region_id, country_id)
VALUES (10, 'LocationTest1', 7, 3);
INSERT INTO location (name, region_id, country_id)
VALUES ('LocationTest2', 7, 3);

insert into trip (location_id, sport_id, price, start_date, end_date)
VALUES (10, 2, 809, '2020-01-12', '2020-02-01');
insert into trip (location_id, sport_id, price, start_date, end_date)
VALUES (10, 1, 708, '2020-05-05', '2020-05-15');

