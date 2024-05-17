CREATE TABLE earner
(
    earner_id  SERIAL      NOT NULL,
    name     VARCHAR(32) NOT NULL,
    surname        VARCHAR(32) NOT NULL,
    email VARCHAR(32) NOT NULL,
    PRIMARY KEY (earner_id),
    UNIQUE (email)
);