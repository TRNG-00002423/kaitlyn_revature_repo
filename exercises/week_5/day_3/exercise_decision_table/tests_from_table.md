# Test Cases
| ID | Description | Input | Expected Output  | Corresponding Rule |
| --- | --- | --- | --- | --- |
| DT-SHIP-01 | Inactive promo leads to 0 discount. | `PromoActive = false`, `CartSubtotal = 100`, `Tier = "Gold"` | `DiscountPercent` = 0  | R1 |
| DT-SHIP-02 | Inactive promo leads to 0 discount when cart subtotal < 75. | `PromoActive = false`, `CartSubtotal = 25`, `Tier="Gold"` | `DiscountPercent` = 0  | R1
| DT-SHIP-03 | Inactive promo leads to 0 when cart subtotal is exactly 75. | `PromoActive = false`, `CartSubtotal = 75`, `Tier="Gold"` | `DiscountPercent` = 0 | R1
| DT-SHIP-04 | Inactive promo with standard membership is 0. | `PromoActive = false`, `CartSubtotal = 100`, `Tier = "Standard"` | `DiscountPercent` = 0 | R1
| DT-SHIP-05 | When cart subtotal is less than 75, the discount should be 0 even when promo is active. | `PromoActive = true`, `CartSubtotal = 60`, `Tier = "Standard"` | `DiscountPercent` = 0 | R2 |
| DT-SHIP-06 | The user being in the gold tier shouldn't stop them from getting no discount when their subtotal is less than 75. | `PromoActive = true`, `CartSubtotal = 60`, `Tier = "Gold"`| `DiscountPercent` = 0 | R2 |
| DT-SHIP-07 | A standard user with an active promo and a subtotal of more than 75 should get a discount of 10%. | `PromoActive = true`, `CartSubtotal = 150`, `Tier = "Standard"` | `DiscountPercent` = 10  | R3|
| DT-SHIP-08 | A standard user with active promo should have a 10% discount in the edge case where their subtotal is exactly 75. | `PromoActive = true`, `CartSubtotal = 75`, `Tier = "Standard"` | `DiscountPercent` = 10 | R3 |
| DT-SHIP-09 | A gold-tier user with an active promo and a subtotal of more than 75 should get a discount of 15%. | `PromoActive = true`, `CartSubtotal = 150`, `Tier = "Gold"` | `DiscountPercent` = 15 | R4 |
| DT-SHIP-10 | A gold-tier user with an active promo should have a 15% discount in the edge case where their subtotal is exactly 75. | `PromoActive = true`, `CartSubtotal = 75`, `Tier = "Gold` | `DiscountPercent` = 15 | R4 |