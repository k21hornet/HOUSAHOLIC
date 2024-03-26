-- DROP TABLE todo_table;

CREATE TABLE IF NOT EXISTS todo_table (
    id              TEXT PRIMARY KEY,
    todo            TEXT NOT NULL,
    user_id         TEXT,
    FOREIGN KEY (user_id) REFERENCES appuser(id) ON DELETE CASCADE,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO todo_table(id,todo,user_id,created_at,updated_at)
VALUES ('1','todo1-1', '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO todo_table(id,todo,user_id,created_at,updated_at)
VALUES ('2','todo1-2', '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO todo_table(id,todo,user_id,created_at,updated_at)
VALUES ('3','todo1-3', '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO todo_table(id,todo,user_id,created_at,updated_at)
VALUES ('4','todo2-1', '2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO todo_table(id,todo,user_id,created_at,updated_at)
VALUES ('5','todo2-2', '2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);