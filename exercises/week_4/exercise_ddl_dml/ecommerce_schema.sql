BEGIN;

DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS customers CASCADE;
DROP TABLE IF EXISTS addresses CASCADE;
DROP TABLE IF EXISTS order_lines CASCADE;


CREATE TABLE customers (
    customer_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL
);

CREATE TABLE products (
	sku VARCHAR(32) PRIMARY KEY,
	price DECIMAL(10, 2) NOT NULL CHECK (price >= 0),
	product_name VARCHAR(255) NOT NULL,
	stock INTEGER NOT NULL CHECK (stock > 0)
);

CREATE TABLE addresses (
	customer_id INTEGER REFERENCES customers(customer_id),
    address_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    street_no INTEGER NOT NULL,
    street_name VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    zip_code VARCHAR(20) NOT NULL,
    state VARCHAR(255) NOT NULL
);

CREATE TABLE orders (
	order_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	address_id INTEGER REFERENCES addresses(address_id),
	customer_id INTEGER REFERENCES customers(customer_id),
	date TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE order_lines (
	order_id INTEGER REFERENCES orders(order_id),
	sku VARCHAR(32) REFERENCES products(sku),
	quantity INTEGER NOT NULL CHECK (quantity > 0),
	unit_price DECIMAL(10, 2) NOT NULL CHECK (unit_price >= 0),
	PRIMARY KEY (order_id, sku)
);

COMMIT;