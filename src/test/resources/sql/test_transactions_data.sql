-- Set up and seed "test" database.

DROP TABLE IF EXISTS customer;
CREATE TABLE customer (
    id INT NOT NULL PRIMARY KEY
    -- Other user stuff...
);

INSERT INTO customer VALUES
(101),
(102);

DROP TABLE IF EXISTS transactions;
CREATE TABLE transactions (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    date_time TIMESTAMP NOT NULL,
    amount NUMERIC (20, 2),

    FOREIGN KEY (customer_id) REFERENCES customer(id)
);

INSERT INTO transactions(customer_id, date_time, amount) VALUES
(101, '2019-11-10', 100.00), --   0 + 50 =  50pts
(101, '2020-01-15', 120.00), --  40 + 50 =  90pts
(101, '2020-02-01', 50.00),  --   0 +  0 =   0pts
(101, '2020-02-20', 205.00), -- 210 + 50 = 270pts
(102, '2020-02-02', 15.00),  --   0 +  0 =   0pts
(102, '2020-03-20', 305.00), -- 410 + 50 = 460pts
(102, '2020-04-20', 105.00); --  10 + 50 =  60pts
