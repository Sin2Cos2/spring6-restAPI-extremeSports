CREATE TABLE country
(
    id   int8 AUTO_INCREMENT PRIMARY KEY,
    name varchar(60)
);

CREATE TABLE region
(
    id         int8 AUTO_INCREMENT PRIMARY KEY,
    name       varchar(90),
    country_id int8,
    FOREIGN KEY (country_id) REFERENCES country (id)
);

CREATE TABLE location
(
    id         int8 AUTO_INCREMENT PRIMARY KEY,
    name       varchar(90),
    region_id  int8,
    country_id int8,
    FOREIGN KEY (region_id) REFERENCES region (id),
    FOREIGN KEY (country_id) REFERENCES country (id)
);

CREATE TABLE sport
(
    id int8 AUTO_INCREMENT PRIMARY KEY,
    name varchar(60)
);

CREATE TABLE sport_location
(
    location_id int8,
    sport_id int8,
    price decimal,
    start_date date,
    end_date date
)

