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
	total_amount numeric (10,2) not null check (total_amount >0),
	constraint fk_orders_customer 
		foreign key (customer_id)
		references customer(customer_id)
);

insert into customer(customer_name) values
('Alice Johnson'),
('Bob Smith'),
('Carol Davis');

insert into orders(customer_id, total_amount) values
(1, 2500.00),
(1, 3500.00),
(2, 5000.00),
(2, 7500.00),
(3, 300.00),
(3, 300.00);


select * from orders;
select * from customer;

-- FUNCTION: calculate discount
-- dollar signs mark start and end of function body
create or replace function calculate_discount_rate (p_customer_id int) 
returns numeric(5, 2)
language plpgsql
as $$
	declare v_total_spent numeric(10, 2);
begin
	select coalesce(sum(total_amount), 0.00)
	into v_total_spent
	from orders
	where customer_id=p_customer_id;
	
	return case
		when v_total_spent >= 10000 then 0.20
		when v_total_spent >= 5000 then 0.10
		when v_total_spent >= 1000 then 0.05
		else 0.00
	end;
end;
$$;
select calculate_discount_rate(1);


-- procedure: apply discount to customer
create or replace procedure apply_customer_discount (p_customer_id int)
language plpgsql
as $$
declare
	v_discount_rate numeric(5, 2);
	v_exists boolean;
begin
	-- check if customer exists
	select exists (select 1 from customer where customer_id=p_customer_id)
	into v_exists;
	
	if not v_exists then
		raise notice 'Customer % not found', p_customer_id;
		return;
	end if;
	
	-- calculate discount
	v_discount_rate := calculate_discount_rate(p_customer_id);
	
	-- update customer
	update customer
	set discount_rate = v_discount_rate,
		discount_updated_at = current_timestamp
	where customer_id = p_customer_id;
	
	raise notice
		'Customer % discount updated to %',
		p_customer_id,
		v_discount_rate * 100
end;
end;
$$;

call apply_customer_discount(1);
call apply_customer_discount(2);
call apply_customer_discount(3);
select * from customer;