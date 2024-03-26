-- DROP TABLE action_table;
-- DROP TABLE category;

CREATE TABLE IF NOT EXISTS category (
    id              TEXT PRIMARY KEY,
    category_name   TEXT NOT NULL,
    is_default      INT,
    user_id         TEXT,
    FOREIGN KEY (user_id) REFERENCES appuser(id) ON DELETE CASCADE,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS action_table (
    id              TEXT PRIMARY KEY,
    is_positive     INT NOT NULL,
    title           TEXT NOT NULL,
    description     TEXT,
    category_id     TEXT,
    FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE,
    point           INT NOT NULL,
    user_id         TEXT,
    FOREIGN KEY (user_id) REFERENCES appuser(id) ON DELETE CASCADE,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO category(id,category_name,is_default,user_id,created_at,updated_at)
VALUES ('1','家事', '1', '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO category(id,category_name,is_default,user_id,created_at,updated_at)
VALUES ('2','運動', '1', '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO category(id,category_name,is_default,user_id,created_at,updated_at)
VALUES ('3','時間浪費', '1', '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO category(id,category_name,is_default,user_id,created_at,updated_at)
VALUES ('4','ゲーム', '1', '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO category(id,category_name,is_default,user_id,created_at,updated_at)
VALUES ('5','その他', '1', '1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO action_table(id,is_positive,title,description,category_id,point,user_id,created_at,updated_at)
VALUES ('1','1', 'トイレ掃除', '', '1','5','1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO action_table(id,is_positive,title,description,category_id,point,user_id,created_at,updated_at)
VALUES ('2','0', 'YouTube', 'shortsを2hくらい', '3','-7','1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);