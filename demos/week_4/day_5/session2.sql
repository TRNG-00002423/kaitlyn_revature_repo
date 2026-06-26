set transaction isolation level read committed;
begin;
update accounts
set balance = 1500
where account_id=1;
commit;