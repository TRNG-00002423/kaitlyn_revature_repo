# Python unittest - the built-in testing framework

# unittest is Python's built-in testing framework (part of standard library)
# follows xUnit pattern - familiar to Java developers
# requires class inheritance from unittest.TestCase

# good for: legacy code, Java developers transitioning, no extra dependencies

# Run with this: 
    #python -m unittest demo_unittest_basics.py
    #python -m unittest demo_unittest_basics.TestCalculator.add -v
    #  

import unittest
from calculator import Calculator

class TestCalculatorBasic(unittest.TestCase):
    """
    Basic calculator tests using unittest.

    NOTE:
    - Class inherits from unittest.TestCase
    - Methods start with 'test_'
    - Uses self.assertEqual, self.assertTrue, etc.
    """

    def setUp(self):
        """
        called before EACH test method
        similar to @BeforeEach in JUnit
        """
        self.calc = Calculator()
    
    def tearDown(self):
        """
        called after EACH test method
        similar to @AfterEach in JUnit
        """
        self.calc = None

    def test_add_returns_sum(self):
        """Test addition with assertEqual"""
        result = self.calc.add(2,3)
        self.assertEqual(5, result)

    def test_subtract_returns_difference(self):
        """Test subtraction"""
        self.assertEqual(7, self.calc.subtract(10, 3))

    def test_multiply_returns_product(self):
        """Test multiplication"""
        self.assertEqual(20, self.calc.multiply(4, 5), "4*5 should equal 20")

    def test_is_even_true(self):
        """Use assertTrue for boolean True"""
        self.assertTrue(self.calc.is_even(2))
        self.assertTrue(self.calc.is_even(0))
        self.assertTrue(self.calc.is_even(-4))

    def test_is_even_false(self):
        """Use assertFalse for boolean False"""
        self.assertFalse(self.calc.is_even(3))
        self.assertFalse(self.calc.is_even(-1))

    def test_greater_than(self):
        """Use assertGreater and assertLess."""
        self.assertGreater(self.calc.add(5, 5), 8)
        self.assertLess(self.calc.subtract(8, 2), 8)
        self.assertGreaterEqual(self.calc.multiply(60, 100), 70)
        self.assertLessEqual(self.calc.divide(5, 2), 3)

    def test_assert_none(self):
        """Use assertNone and assertIsNotNone"""
        result = self.calc.add(1, 2)
        self.assertIsNotNone(result)
    
    def test_assert_type(self):
        """Use assertIsInstance for type checking"""
        result = self.calc.divide(10, 3)
        self.assertIsInstance(result, float)

    #Exception Testing
    def test_divide_by_zero_raises_exception(self):
        """Use assertRaises to test exceptions
        can be used as a context manager (recommended) or callable"""
        with self.assertRaises(ZeroDivisionError):
            self.calc.divide(10, 0)
    
    def test_divide_by_zero_exception_message(self):
        with self.assertRaises(ZeroDivisionError) as context:
            self.calc.divide(5, 0)

        self.assertIn("zero", str(context.exception).lower())

    def test_negative_exponent_raises_value_error(self):
        """Test ValueError for negative exponent."""
        with self.assertRaises(ValueError) as context:
            self.calc.power(2, -1)
        
        self.assertIn("negative", str(context.exception).lower())

class TestCalculatorAdvanced(unittest.TestCase):
    """More advanced calculator tests."""

    @classmethod
    def setUpClass(cls):
        """
        Called once before all tests in this class.
        Similar to @BeforeAll in JUnit5.
        """
        print("\n--- Setting up TestCalculatorAdvanced class ---")
        cls.shared_calc = Calculator()

    @classmethod
    def tearDownClass(cls):
        """
        Called once after all tests in this class.
        Similar to @AfterAll in JUnit5.
        """
        print("--- Tearing down TestCalculatorAdvanced class ---")
        cls.shared_calc = None

    def test_power_calculations(self):
        """Use the class-level calculator."""
        self.assertEqual(8, self.shared_calc.power(2, 3))
        self.assertEqual(100, self.shared_calc.power(10, 2))

    def test_edge_cases(self):
        """Multiple assertions in one test."""
        self.assertEqual(1, self.shared_calc.power(5, 0))
        self.assertEqual(5, self.shared_calc.power(5, 1))


class TestFloatingPoint(unittest.TestCase):
    """Demonstrate floating-point comparison."""

    def test_almost_equal(self):
        """
        Use assertAlmostEqual for floating-point comparisons.
        
        This is like assertEquals with delta in JUnit5.
        """
        calc = Calculator()
        result = calc.divide(10, 3)  # 3.333...
        
        # Check to 2 decimal places (default is 7)
        self.assertAlmostEqual(3.33, result, places=2)
        
        # Alternative: specify delta
        self.assertAlmostEqual(3.333, result, delta=0.001)


# Run tests when executing this file directly
if __name__ == "__main__":
    unittest.main(verbosity=2)