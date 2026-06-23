-- transactions
-- follow ACID properties: atomic, consistent, isolated, and durable
-- atomic: either everything succeeds or everything fails
-- consistent: database is in a valid state before and after transaction
-- isolated: transactions don't interfere with each other
-- durable: data must not be lost when the system fails

SELECT * FROM product;

SELECT * FROM customer;
BEGIN;

UPDATE product SET stock_qty = stock_qty - 1  WHERE sku='MUG-01' AND stock_qty >= 1;
ROLLBACK;

BEGIN;
INSERT INTO customer (email, full_name) VALUES ('txn-demo@example.com', 'Txn Demo');

UPDATE product
SET stock_qty = stock_qty - 1
WHERE sku = 'MUG-01'
AND stock_qty >= 1;

COMMIT;

--SAVEPOINT
BEGIN;
SAVEPOINT before_insert;
INSERT INTO order_header(customer_id, status)
SELECT customer_id, 'OPEN' FROM customer WHERE email = 'txn-demo@example.com';

SELECT * FROM order_header;
ROLLBACK TO SAVEPOINT before_insert;

INSERT INTO order_line (order_id, line_no)