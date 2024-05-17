CREATE TABLE earning
(
    earning_id SERIAL                    NOT NULL,
    name        VARCHAR(20)              NOT NULL,
    salary      NUMERIC(7, 2)            NOT NULL,
    earner_id   INT                      NOT NULL,
    PRIMARY KEY (earning_id),
        CONSTRAINT fk_earning_earner
                FOREIGN KEY (earner_id)
                    REFERENCES earner (earner_id)
);