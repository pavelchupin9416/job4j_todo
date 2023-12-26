CREATE TABLE tasks (
   id SERIAL PRIMARY KEY,
   title  varchar not null,
   description TEXT,
   created TIMESTAMP,
   done BOOLEAN,
   user_id     int     REFERENCES todo_user(id)    NOT NULL
);