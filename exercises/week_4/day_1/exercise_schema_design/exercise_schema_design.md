# Exercise: E-commerce schema design (normalized)

## Your tasks

1. **List entities** you need (tables). Include any **junction** or **child** tables required for many-to-many or one-to-many rules above.

Main entities:
- Customers
- Products
- Orders

Child entities:
- Order lines (child of orders)

Junction tables:
- Customers+addresses (many-to-many relationship)


2. For each table, list **primary key** strategy (surrogate vs natural) and important **alternate keys** (`UNIQUE`).
- Customer: `customer_id` (surrogate)
- Product: `sku` (natural)
- Order: `order_id` (surrogate)
- Address: `address_id` (surrogate)
- Order line: `(order_id, sku)` (surrogate, natural)
- Customer address: `(customer_id, address_id)` (surrogate, surrogate)

3. Draw an **ERD** with **cardinality** (1:1, 1:N, M:N). Use the template:
   - `templates/ecommerce_erd.mermaid`
4. **Write 5–8 bullet “design decisions”**, e.g.:
- Customers have surrogate IDs as primary keys instead of their emails.
- Order lines have a price field to reflect the price of the product when it was purchased, even if the price of the item has since changed.
- When an order is deleted, the order lines that reference it will be deleted as well. (on delete, cascade)
- Deleting a customer deletes associated addresses, and orders made by that user have the user id field set to NULL.
- Deleting a product is not allowed to protect the integrity of the database. When you use this database, discontinued products should have their stock set to 0 rather than be removed.
- Deleting an order line causes no additional actions.
