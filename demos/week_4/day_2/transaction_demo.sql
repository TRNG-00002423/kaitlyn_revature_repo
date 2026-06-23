-- transactions
-- follow ACID properties: atomic, consistent, isolated, and durable
-- atomic: either everything succeeds or everything fails
-- consistent: database is in a valid state before and after transaction
-- isolated: transactions don't interfere with each other
-- durable: data must not be lost when the system fails

BEGIN;