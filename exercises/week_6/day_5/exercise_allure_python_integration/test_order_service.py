# test_order_service.py - ADD ALLURE DECORATORS
import allure
import pytest
from order_service import OrderService, OrderNotFoundError

@allure.epic("E-Commerce")
@allure.feature("Order Service")
class TestOrderService:
    
    @pytest.fixture(autouse=True)
    def setup(self):
        self.service = OrderService()
    
    @allure.story("Create an order")
    @allure.severity(allure.severity_level.CRITICAL)
    @allure.title("Create a valid order")
    def test_create_order_returns_id(self):
        order_id = self.service.create_order(
            items=[{"sku": "SKU1", "qty": 1}],
            customer_id=100
        )
        assert order_id is not None
        assert isinstance(order_id, int)
    
    @allure.story("Orders must have at least one item")
    @allure.severity(allure.severity_level.NORMAL)
    @allure.title("Trying to create an order with no items throws an error")
    def test_create_order_empty_items_raises(self):
        with pytest.raises(ValueError):
            self.service.create_order(items=[], customer_id=100)
    

    @allure.story("Orders can be retrieved from the order service")
    @allure.severity(allure.severity_level.CRITICAL)
    @allure.title("Newly-added order can be retrieved from order service")
    def test_get_order_existing_returns_order(self):
        order_id = self.service.create_order(
            items=[{"sku": "SKU1", "qty": 1}],
            customer_id=100
        )
        order = self.service.get_order(order_id)
        assert order is not None
        assert order.id == order_id
    
    @allure.story("Only existent orders should be able to be retrieved")
    @allure.severity(allure.severity_level.NORMAL)
    @allure.title("Getting a non-existent order throws an error")
    def test_get_order_nonexistent_raises(self):
        with pytest.raises(OrderNotFoundError):
            self.service.get_order(99999)
    
    @allure.story("Pending orders can be cancelled")
    @allure.severity(allure.severity_level.NORMAL)
    @allure.title("Cancelling a pending order succeeds")
    def test_cancel_order_pending_succeeds(self):
        order_id = self.service.create_order(
            items=[{"sku": "SKU1", "qty": 1}],
            customer_id=100
        )
        result = self.service.cancel_order(order_id)
        assert result is True
    
    @allure.story("Completing orders can no longer be cancelled")
    @allure.severity(allure.severity_level.CRITICAL)
    @allure.title("Cancelling a completed order fails")
    def test_cancel_order_completed_fails(self):
        order_id = self.service.create_order(
            items=[{"sku": "SKU1", "qty": 1}],
            customer_id=100
        )
        self.service.complete_order(order_id)
        
        with pytest.raises(ValueError, match="Cannot cancel"):
            self.service.cancel_order(order_id)
    
    @allure.story("Customers can get totals for orders for multiple items")
    @allure.severity(allure.severity_level.NORMAL)
    @allure.title("Calculate total for multiple items")
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