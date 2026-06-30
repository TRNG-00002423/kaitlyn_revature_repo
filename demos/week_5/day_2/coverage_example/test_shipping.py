# pytest test_shipping.py
# pytest test_shipping.py --cov=shipping -cov-report=html

import pytest 
from shipping import get_shipping_tier

def test_express_returned_for_large_priority_order():
    assert get_shipping_tier(150.00, priority=True) == "EXPRESS"

def test_standard_returned_for_large_nonpriority_order():
    assert get_shipping_tier(120.00, priority=False) == "STANDARD"

def no_free_shipping_returned_for_small_order():
    assert get_shipping_tier(50.00, priority=False) == "NO FREE SHIPPING"