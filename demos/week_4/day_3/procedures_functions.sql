set search_path to test1;

drop table if exists orders cascade;
drop table if exists customer cascade;

create table customer (
	customer_id int generated always as identity primary key,
	customer_name varchar(100) not null,
	discount_rate numeric(5, 2) default 0.0,
	discount_updated_at timestamp
);

create table orders (
	order_id int generated always as identity primary key,
	customer_id int not null,
	order_date date not null default current_date,
	total_amount numeric (5,2) not null check (total_amount >0),
	constraint fk_orders_customer 
		foreign key (customer_id)
		references customer(customer_id)
);

