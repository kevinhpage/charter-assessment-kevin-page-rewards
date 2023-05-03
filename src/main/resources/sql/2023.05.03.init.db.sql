-- Insert initial data into "real" database.
-- TODO: Implement schema evolution e.g. Flyway.

INSERT INTO customer(id) VALUES
(1),
(2);

INSERT INTO transactions(customer_id, date_time, amount) VALUES
     (1, '2019-12-01', 100.00), --   0 + 50 =  50pts
     (1, '2020-02-25', 120.00), --  40 + 50 =  90pts
     (1, '2020-03-01', 50.00),  --   0 +  0 =   0pts
     (1, '2020-04-20', 205.00), -- 210 + 50 = 270pts
     (2, '2020-03-02', 15.00),  --   0 +  0 =   0pts
     (2, '2020-04-20', 305.00), -- 410 + 50 = 460pts
     (2, '2020-05-22', 105.00); --  10 + 50 =  60pts
