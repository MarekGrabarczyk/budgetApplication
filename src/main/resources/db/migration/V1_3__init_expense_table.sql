CREATE TABLE expense
(
    expense_id SERIAL                   NOT NULL,
    name        VARCHAR(20)             NOT NULL,
    price       NUMERIC(7, 2)           NOT NULL,
    earner_id   INT                     NOT NULL,
    category_id INT                     NOT NULL,
    PRIMARY KEY (expense_id),
    CONSTRAINT fk_expense_earner
            FOREIGN KEY (earner_id)
                REFERENCES earner (earner_id),
    CONSTRAINT fk_expense_category
            FOREIGN KEY (category_id)
                REFERENCES category (category_id)
);