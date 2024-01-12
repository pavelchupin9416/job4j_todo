ALTER TABLE todo_user
    ADD COLUMN IF NOT EXISTS
    user_zone text NOT NULL;