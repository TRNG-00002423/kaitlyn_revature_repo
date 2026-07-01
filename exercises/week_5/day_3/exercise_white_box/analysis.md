# Analysis

## Statement Coverage
A minimum of 4 tests are required in order to execute every reachable statement in `shipping_eligibility.py`.
Example input tuples:
- **T1:** `(cart_subtotal=30, region="DE", is_member=True)` (any tuple where region is "FR", "DE", or "NL")
- **T2:** `(cart_subtotal=90, region="BR", is_member=False)` (any tuple where region is not "FR", "DE", "NL", or "US")
- **T3:** `(cart_subtotal=40, region="US", is_member=True)` (cart_subtotal is greater than or equal to 35, region is "US")
- **T4:** `(cart_subtotal=20, region="US", is_member=False)` (cart_subtotal is less than 35 and region is "US")

Line 23 is currently unreachable regardless of the input tuple. The condition to reach line 23 requires that both `cart_subtotal<35` and `cart_subtotal>=50`, which isn't possible.

As a consequence of the short-circuit in line 22's condition makes it so the value of `is_member` is never read and never impacts the output of `shipping_eligibility`.

## Decision Coverage

A minimum of 6 tests are required to test the times when each decision is evaluated to `True` or `False`. This accounts for unreachable code. The condition `is_member and cart_subtotal >= 50` in line 22 will never evaluate to be `True`.

## Condition Coverage

`is_member == True`: T1 and T3
`is_member == False`: T2 and T4
`cart_subtotal >= 50`: T2
`cart_subtotal < 50`: T1, T3, T4 

## Note on branch coverage
If I achieved 100% branch coverage, then the logic could still be incorrect for the business. Ensuring branch coverage is a *verification* measure, not a *validation* measure, and it doesn't directly effect the end user's experience when using the product. Full branch coverage won't save a product when the specification itself is correct.