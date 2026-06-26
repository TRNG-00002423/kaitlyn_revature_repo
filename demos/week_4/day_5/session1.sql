drop table if exists accounts;

create table accounts (
	account_id int primary key,
	customer_id varchar(50),
	balance decimal(10,2)
);

insert into accounts values
(1, 'Alice', 1000),
(2, 'Bob', 500),
(3, 'Charlie', 700);

select * from accounts;

-- dirty read: A transaction reads data that has another transaction ahs modified but not committed
-- postgresql has lowest isolation level as READ COMMITTED (default) and will not allow dirty reads
-- if it has READ UNCOMMITTED, then this would be possible

-- non-repeatable read
-- read the same row twice in one transaction and get different values because another
-- transaction committed changes

-- phantom read: you execute the same read twice and extra rows appear the second time
-- SERIALIZABLE prevents phantom reads

set transaction isolation level repeatable read;

begin transaction;

select balance from accounts where account_id = 1;

commit;
