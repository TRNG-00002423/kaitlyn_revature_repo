class Order:
    items: list[dict]
    customer_id: int
    total: float
    def __init__(self, items: list[dict], customer_id: int, id: int):
        self.items = items
        self.customer_id = customer_id
        self.total = 0.0
        self.id = id
        for item in items:
            if "price" in item:
                multiplier = 1
                if "qty" in item:
                    multiplier = item["qty"]
                self.total += (item["price"] * multiplier)

class OrderService:
    cur_id = 1
    orders = {}

    def create_order(self, items: list[dict], customer_id: int)-> int:
        if len(items) == 0:
            raise ValueError("Cannot create an order with no items.")
        ret_id = self.cur_id
        self.cur_id += 1
        self.orders[ret_id] = Order(items=items, customer_id=customer_id, id=ret_id)
        return ret_id
    
    def get_order(self, order_id: int) -> Order:
        if order_id not in self.orders:
            raise OrderNotFoundError(f"order with id {order_id} not found")
        return self.orders[order_id]
    
    def cancel_order(self, order_id: int) -> bool:
        """Returns True if the order was successfully cancelled"""
        if order_id not in self.orders:
            raise ValueError("Cannot cancel")
        del self.orders[order_id]
        return True

    def complete_order(self, order_id: int) -> bool:
        if order_id not in self.orders:
            raise ValueError("cannot complete order")
        del self.orders[order_id]
        return True
    
class OrderNotFoundError(Exception):
    def __init__(self, message):
        super().__init__(message)

