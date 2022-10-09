ALTER TABLE sport_location
    ADD CONSTRAINT location_fk FOREIGN KEY (location_id) REFERENCES location (id);
ALTER TABLE sport_location
    ADD CONSTRAINT sport_fk FOREIGN KEY (sport_id) REFERENCES sport (id);