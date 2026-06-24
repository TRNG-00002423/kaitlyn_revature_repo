-- views and indexes
drop view if exists v_order_line_detail;
drop view if exists v_customer_spend;
select * from order_header;

create view v_order_line_detail as
select c.email, oh.order_id, p.sku, (ol.qty * ol.unit_price) as line_total, oh.status from customer c
inner join order_header oh on oh.customer_id = c.customer_id
inner join order_line ol on ol.order_id = oh.order_id
inner join product p on p.product_id = ol.product_id;

select * from v_order_line_detail;
-- example row: "alpha@example.com"	1	"BASE-A"	20.00	"PAID"

select count(order_id) from order_header group by customer_id;
select sum(line_total) from v_order_line_detail group by email;

create view v_customer_spend as
select c.email, count(v.order_id), sum(v.line_total) as total_spend from customer c
left join v_order_line_detail v on c.email = v.email
group by c.email;

select * from v_customer_spend;
-- example row: "alpha@example.com"	2	40.00
-- This view isn't a substitute for an index on the base table because it is missing the customer
-- id that is notmally required for information on each customer.

drop index if exists product_event_id_index;

explain (analyze, buffers) select count(*) from product_event where product_id = 1;
-- This returns a Seq Scan.


create index product_event_id_index
on product_event (product_id);

explain (analyze, buffers) select count(*) from product_event where product_id = 1;
-- This returned an Index Only Scan.
/*
What changed:
Before, this query would search through every row, leading to a pretty slow execution time. The
total time it took to iterate through these rows was 2.119 ms of planning time and 4.194 ms of
execution time. Adding the index over product_id made it a lot faster and more efficient to filter
through the product_event by product_id. The index-only scan yielded a planning time of 0.284 ms
and an execution time of 1.764 ms, less than half of the time required for the sequential scan.
The main downside to this index is that insertions and updates will be made slower due to the fact
that the index needs to be updated every time the table is.
*/

select * from product_event;
drop index if exists product_event_composite_index;

explain (analyze, buffers) select count(*) from product_event where product_id = 1 and event_type = 'VIEW';

create index product_event_composite_index
on product_event (product_id, event_type);

explain (analyze, buffers) select count(*) from product_event where product_id = 1 and event_type = 'VIEW';

