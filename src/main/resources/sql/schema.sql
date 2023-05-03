-- Initialize the schema.  This should only be run ONCE per environment.
-- Changes to schema should be written to date-stamped .sql files.
-- TODO: Implement schema evolution e.g. Flyway.

DROP TABLE IF EXISTS customer;
CREATE TABLE customer (
    id INT NOT NULL PRIMARY KEY
    -- Other user stuff...
);

DROP TABLE IF EXISTS transactions;
CREATE TABLE transactions (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    date_time TIMESTAMP NOT NULL,
    amount NUMERIC (20, 2),

    FOREIGN KEY (customer_id) REFERENCES customer(id)
);
