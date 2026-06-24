-- exercise_stored_procedures

create or replace procedure adjust_stock (p_sku TEXT, p_delta INT)
language plpgsql
as $$
declare
	v_cur_stock int;
	v_new_stock int;
	v_exists boolean;
begin
	select exists (select 1 from product where sku = p_sku)
	into v_exists;
	
	if not v_exists then
		raise notice 'SKU % not found', p_sku;
		return;
	end if;
	
	v_cur_stock := (select stock_qty from product where sku = p_sku limit 1);
	v_new_stock := v_cur_stock + p_delta;
	
	if v_new_stock < 0 then
		raise notice 'adjust_stock cannot result in a negative stock %', v_new_stock;
		return;
	end if;
	
	update product
	set stock_qty = v_new_stock
	where sku = p_sku;
	
	raise notice 'Stock of item % updated to %', p_sku, v_new_stock;
	end;
$$;


-- Option B: function that returns NUMERIC
create or replace function fetch_order_total (p_order_id int)
returns numeric(10, 2)
language plpgsql
as $$
declare
	v_exists boolean;
	v_sum numeric(10, 2);
	v_num_lines int;
	v_total numeric(10, 2);
begin
	select exists (select 1 from order_header where order_id = p_order_id)
	into v_exists;
	
	if not v_exists then
		raise notice 'Order with id % does not exist', p_order_id
		return;
	end if;
	
	select sum(unit_price * qty) from order_line
	where order_id = p_order_id
	into v_sum;
	
	select count(*) from order_line
	where order_id = p_order_id
	into v_num_lines;
	
	v_total := v_sum / v_num_lines;
	
	return v_total;
end;
$$;


-- calls
select * from product;

-- successful restock
call adjust_stock('BASE-A', 50);
-- successful stock removal
call adjust_stock('BASE-A', -50);
-- fail on insufficient stock
call adjust_stock('BASE-A', -999);

-- return the totals of existing orders
select fetch_order_total(1);
select fetch_order_total(2);
select fetch_order_total(3);

-- fails on nonexistent order id
select fetch_order_total(4);
	
	