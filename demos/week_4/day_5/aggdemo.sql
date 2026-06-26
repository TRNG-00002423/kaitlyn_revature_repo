
select * from order_line;
select * from order_header;

-- customer with highest total line quantity

select c.email
from customer c
where customer_id = 
(
	select oh.customer_id
	from order_line ol
	join order_header oh
	on oh.order_id = ol.order_id
	group by oh.customer_id
	order by sum(ol.qty) desc
	limit 1
);

-- instead, get the total quantity returned

select c.email, sum(ol.qty) as total_qty
from customer c
join order_header oh on c.customer_id = oh.customer_id
join order_line ol on ol.order_id = oh.order_id
group by c.customer_id, c.email
order by total_qty desc;


-- aggregates + group by + having: revenue per customer above a threshold

select c.email,
	sum(ol.qty * ol.unit_price) as revenue,
	count(distinct oh.order_id) as order_count
from customer c
join order_header oh on oh.customer_id = c.customer_id
join order_line ol on ol.order_id = oh.order_id
group by c.customer_id
order by revenue desc;

-- subquery in FROM: per-order line counts (foundation for reporting)
select oh.order_id, c.email, lc.line_count,
sum (ol.qty * ol.unit_price) as order_revenue
from order_header oh
join customer c on c.customer_id = oh.customer_id
join order_line ol on ol.order_id = oh.order_id
join (select order_id, count(*) as line_count
	 from order_line
	 group by order_id) lc on lc.order_id = oh.order_id
group by oh.order_id, c.email, lc.line_count;