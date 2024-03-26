-- DROP TABLE appuser;

CREATE TABLE IF NOT EXISTS appuser (
    id              TEXT PRIMARY KEY,
    username        TEXT NOT NULL,
    email           TEXT NOT NULL UNIQUE,
    password_hash   TEXT NOT NULL,
    user_icon       TEXT,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- pass:smithsmith
INSERT INTO appuser(id,username,email,password_hash,created_at,updated_at)
VALUES ('1','smith01', '01@smith.com', '$2a$10$.i6CfLWSMlU8Uv4cx3uwQ.TSzylAHry2qJ/rf1/ZLRGA9HgahVeXe', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO appuser(id,username,email,password_hash,created_at,updated_at)
VALUES ('2','smith02', '02@smith.com', '$2a$10$.i6CfLWSMlU8Uv4cx3uwQ.TSzylAHry2qJ/rf1/ZLRGA9HgahVeXe', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);