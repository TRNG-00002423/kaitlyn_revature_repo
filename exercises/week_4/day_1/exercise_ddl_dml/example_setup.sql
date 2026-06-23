BEGIN;

INSERT INTO customers (email, first_name, last_name, phone)
VALUES ('john@mail.com', 'John', 'Consumer', '123-456-7890'), ('carol@mail.com', 'Carol', 'Holiday', '122-512-2512');

INSERT INTO products (sku, price, product_name, stock)
VALUES ('TV-CRT3NN4-30', 399.99, 'TV', 1), ('LT-Q33N-15', 1099.99, 'Laptop', 4), ('CRDS-K1N6-52', 6.99, 'Deck of cards', 3);

INSERT INTO addresses (customer_id, street_no, street_name, city, zip_code, state)
VALUES (1, 123, 'Main St', 'Springfield', '01101', 'MA'), (2, 1225, 'Christmas Rd', 'Hometown', '12345', 'NH');

INSERT INTO orders (address_id, customer_id)
VALUES (1, 1), (2, 2);

INSERT INTO order_lines (order_id, sku, quantity, unit_price)
VALUES (1, 'TV-CRT3NN4-30', 1, 399.99), (2, 'LT-Q33N-15', 4, 1099.99), (2, 'CRDS-K1N6-52', 2, 6.99);

UPDATE products
SET price=1199.99
WHERE sku='LT-Q33N-15';

DELETE FROM customers
WHERE customer_id=1;

COMMIT;