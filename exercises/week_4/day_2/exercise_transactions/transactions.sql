-- Transactions

delete from order_header where customer_id=3 and status='PAID';
-- Section A: Happy Path
begin;

  DO $$
  DECLARE 
    last_id INT;
  BEGIN
    -- Insert a row and get the inserted ID
    insert into order_header (customer_id, status) values(3, 'PAID') returning order_id into last_id;      
    insert into order_line (order_id, line_no, product_id, qty, unit_price)
	values(last_id, 1, 1, 3, 10.00);
	update product set stock_qty = stock_qty - 1 where product_id = 3;
	RAISE NOTICE '%', last_id;
  END $$;

commit;
select * from order_header;
select * from order_line;
select * from product;


-- Section B: Rollback on Rule Violation
begin;

  DO $$
  DECLARE 
    last_id INT;
  BEGIN
    -- Insert a row and get the inserted ID
    insert into order_header (customer_id, status) values(3, 'PAID') returning order_id into last_id;      
    insert into order_line (order_id, line_no, product_id, qty, unit_price)
	values(last_id, 1, 1, 9000, 10.00);
	update product set stock_qty = stock_qty - 1 where product_id = 9000;
	RAISE NOTICE '%', last_id;
  END $$;

rollback;


-- Section C: Savepoint
begin;
savepoint before_header;
insert into order_header(customer_id, status) values (3, 'bad status');
rollback to savepoint before_header;
insert into order_header(customer_id, status) values (3, 'OPEN');
commit;
