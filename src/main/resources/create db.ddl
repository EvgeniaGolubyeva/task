CREATE SCHEMA IF NOT EXISTS task;

CREATE TABLE IF NOT EXISTS task.messages
(
    id INT PRIMARY KEY NOT NULL,
    text VARCHAR(45) NOT NULL,
    userId INT NOT NULL
);
CREATE TABLE IF NOT EXISTS task.users
(
    id INT PRIMARY KEY NOT NULL,
    name VARCHAR(45)
);
ALTER TABLE task.messages ADD FOREIGN KEY ( userId ) REFERENCES task.users ( id );
CREATE INDEX user_idx ON task.messages ( userId );
CREATE UNIQUE INDEX unique_name ON task.users ( name );