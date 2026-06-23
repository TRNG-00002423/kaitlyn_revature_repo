-- 1: Inner Join
select order_header.order_id, placed_at, email, sum(qty * unit_price) as revenue
from order_header 
inner join customer on customer.customer_id = order_header.customer_id
inner join order_line on order_header.order_id = order_line.order_id
group by email, order_header.order_id;


-- 2: Left Join
select full_name, max(placed_at) as most_recent_order
from customer
left join order_header on customer.customer_id = order_header.customer_id
group by full_name;

-- 3: Right Join
select full_name, max(placed_at) as most_recent_order
from order_header
right join customer on customer.customer_id = order_header.customer_id
group by full_name;

-- 4: Full Outer Join
select full_name, order_id
from customer
full outer join order_header on customer.customer_id = order_header.customer_id;
-- An "orphan order" with a NULL customer ID would indicate an order made by a user that has
-- since been removed from the database.

-- 5: Cross Join (controlled)
select name, status from product
cross join (
	select * from (VALUES(1, 'STOCK_OK'), (2, 'STOCK_LOW'), (3, 'STOCK_OK')) as prod_status(product_id, status)
) prod_status
where prod_status.product_id = product.product_id;


-- 6: Aggregate + Having
select customer.customer_id, full_name, sum(qty*unit_price) as total_spent from customer
inner join order_header on order_header.customer_id = customer.customer_id
inner join order_line on order_line.order_id = order_header.order_id
group by customer.customer_id
having sum(qty*unit_price) > 25;

-- 7: Subquery
select name from product
where product_id in 
(select product_id from order_line group by product_id
having count(distinct(order_id)) > 1);

-- 8: Set operation
select *, 'active' as activity from customer
where customer_id in (select distinct(customer_id) from order_header)
union all
select *, 'inactive' as activity from customer
where customer_id not in (select distinct(customer_id) from order_header);


-- left join on customer/order header: keep all customers, even with no matching order header
-- inner join on customer/order header: only keep customers with at least one matching order header