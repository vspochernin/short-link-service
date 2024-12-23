CREATE TABLE users
(
    id UUID PRIMARY KEY
);

CREATE TABLE links
(
    id                  SERIAL PRIMARY KEY,
    user_id             UUID         NOT NULL,
    long_url            VARCHAR(255) NOT NULL,
    short_url           VARCHAR(31)  NOT NULL,
    max_clicks          INTEGER      NOT NULL,
    expiration_datetime TIMESTAMP    NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
