ALTER TABLE tasks DROP COLUMN created;
ALTER TABLE tasks ADD COLUMN created timestamp without time zone DEFAULT now();