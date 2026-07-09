# pytest fixtures - setup, teardown, and dependency injection
# fixtures are pytest's answers to setup/teardown - but WAY more powerful

# use "yield" for setup/teardown pattern
# scopes control fixture lifetime: function, class, module, session
# conftest.py (always named this) shares fixtures across text files

# run this with:
#   pytest demo_fixtures_intro.py -v
#   pytest demo_fixtures_intro.py -v -s # shows print statements

import pytest
from calculator import Calculator, StringCalculator

# basic fixtures
# each test gets a fresh calculator instance
@pytest.fixture
def calculator():
    """provide a calculator instance for tests"""
    return Calculator()

@pytest.fixture
def string_calculator():
    """provide a string calculator instance"""
    return StringCalculator()

def test_add_with_fixture(calculator):
    """Calculator is automatically injected"""
    result = calculator.add(2, 3)
    assert result == 5

def test_subtract_with_fixture(calculator):
    """Test subtract with fixture"""
    result = calculator.subtract(6, 7)
    assert result == -1 

def test_string_add(string_calculator):
    result = string_calculator.add("1,2,3")
    assert result == 6

# fixtures with setup and teardown

@pytest.fixture
def temp_file(tmp_path):
    """Create a temp file, provide it, and clean it up
    tmp_path is a built-in pytest fixture"""
    file_path = tmp_path / "test_data.txt"
    # file_path = "test_data.txt"
    file_path.write_text("test content")
    print(f"\n[SETUP] created temp file: {file_path}")

    yield file_path

    print(f"[TEARDOWN] Cleaning up: {file_path}")
    if file_path.exists():
        file_path.unlink() 

def test_temp_file_exists(temp_file):
    assert temp_file.exists()
    assert temp_file.read_text() == "test content"

def test_temp_file_can_be_modified(temp_file):
    temp_file.write_text("modified content")
    assert temp_file.read_text() == "modified content"

@pytest.fixture(scope="function")
def function_scoped_calc():
    print(f"\n[FUNCTION FIXTURE] Creating calculator")
    calc = Calculator()
    yield print("[FUNCTION FIXTURE] cleaning up")

@pytest.fixture(scope = "module")
def module_scoped_calc():
    print(f"\n[MODULE FIXTURE] Creating calculator (once per module)")
    calc = Calculator()
    yield calc
    print(f"\n[MODULE FIXTURE] cleaning up")

@pytest.fixture(scope = "class")
def class_scoped_calc():
    print(f"\n[CLASS FIXTURE] cleaning up")

class test_module_scoped:
    """tests sharing test_module_scoped fixture"""
    def test_module_1(self, module_scoped_calc):
        assert module_scoped_calc.add(1, 1) == 2
    
    def test_module_2(self, module_scoped_calc):
        """same calculator instance as test_module_1"""
        assert module_scoped_calc.add(2, 2) == 4