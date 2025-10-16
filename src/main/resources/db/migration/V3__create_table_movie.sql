CREATE TABLE movie(
 id serial PRIMARY KEY,
 tittle VARCHAR(255) NOT NULL,
 description text,
 release_date date,
 rating numeric,
 create_at timestamp,
 update_at timestamp
);