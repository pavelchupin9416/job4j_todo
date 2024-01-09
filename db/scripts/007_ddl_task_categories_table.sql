CREATE TABLE task_categories (
   id SERIAL PRIMARY KEY,
   tasks_id int REFERENCES tasks(id) NOT NULL,
   categories_id int REFERENCES categories(id) NOT NULL,
   unique (tasks_id, categories_id)
);