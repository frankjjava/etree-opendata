SET search_path TO opendata_tbls;

ALTER TABLE timezones_countries ALTER COLUMN timezone_id TYPE varchar(20);
