# test_order_service.py - ADD ALLURE DECORATORS
import pytest
from order_service import OrderService, OrderNotFoundError


class TestOrderService:
    
    @pytest.fixture(autouse=True)
    def setup(self):
        self.service = OrderService()
    
    def test_create_order_returns_id(self):
        order_id = self.service.create_order(
            items=[{"sku": "SKU1", "qty": 1}],
            customer_id=100
        )
        assert order_id is not None
        assert isinstance(order_id, int)
    
    def test_create_order_empty_items_raises(self):
        with pytest.raises(ValueError):
            self.service.create_order(items=[], customer_id=100)
    
    def test_get_order_existing_returns_order(self):
        order_id = self.service.create_order(
            items=[{"sku": "SKU1", "qty": 1}],
            customer_id=100
        )
        order = self.service.get_order(order_id)
        assert order is not None
        assert order.id == order_id
    
    def test_get_order_nonexistent_raises(self):
        with pytest.raises(OrderNotFoundError):
            self.service.get_order(99999)
    
    def test_cancel_order_pending_succeeds(self):
        order_id = self.service.create_order(
            items=[{"sku": "SKU1", "qty": 1}],
            customer_id=100
        )
        result = self.service.cancel_order(order_id)
        assert result is True
    
    def test_cancel_order_completed_fails(self):
        order_id = self.service.create_order(
            items=[{"sku": "SKU1", "qty": 1}],
            customer_id=100
        )
        self.service.complete_order(order_id)
        
        with pytest.raises(ValueError, match="Cannot cancel"):
            self.service.cancel_order(order_id)
    
    def test_calculate_total_multiple_items(self):
        order_id = self.service.create_order(
            items=[
                {"sku": "SKU1", "qty": 2, "price": 10.00},
                {"sku": "SKU2", "qty": 1, "price": 25.00}
            ],
            customer_id=100
        )
        order = self.service.get_order(order_id)
        assert order.total == 45.00  # (2*10) + (1*25)