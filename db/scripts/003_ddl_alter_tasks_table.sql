ALTER TABLE tasks
    ADD COLUMN IF NOT EXISTS
    user_id     int     REFERENCES todo_user(id)    NOT NULL DEFAULT 1;