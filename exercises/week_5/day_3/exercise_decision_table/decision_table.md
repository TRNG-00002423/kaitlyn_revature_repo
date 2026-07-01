# Decision Table

| | R1 | R2 | R3 | R4 |
| --- | --- | --- | --- | --- |
| C1: PromoActive? | N | - | Y | Y |
| C2: CartSubtotal ≥ 75? | - | N | Y | Y |
| C3: Tier? | - | - | Standard | Gold
| A1: DiscountPercent = 0 | X | X | | |
| A2: DiscountPercent = 10 | | | X | |
| A3: DiscountPercent = 15 | | | | X |

## Explanation for -
- When PromoActive=N, DiscountPercent is always 0, regardless of any other condition.
- When CartSubtotal < 75, DiscountPercent is always 0, regardless of any other condition.