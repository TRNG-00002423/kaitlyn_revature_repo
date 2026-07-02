import pytest
from loan_eligibility import assess_loan

def test_happy_path_eligible():
    '''
    All conditions False -> ELIGIBLE. One test, many uncovered branches.
    '''
    assert assess_loan(30, 50000, 700, True) == "ELIGIBLE"

def test_ineligible_age_too_young():
    assert assess_loan(4, 26000, 800, True) == "INELIGIBLE_AGE"

def test_ineligible_age_too_old():
    assert assess_loan(80, 26000, 800, True) == "INELIGIBLE_AGE"

def test_ineligible_income():
    assert assess_loan(53, 1, 750, True) == "INELIGIBLE_INCOME"

def test_ineligible_credit():
    assert assess_loan(60, 70000, 400, True) == "INELIGIBLE_CREDIT"

def test_ineligble_employment():
    assert assess_loan(19, 30000, 700, False) == "INELIGIBLE_EMPLOYMENT"

@pytest.mark.parametrize("age, expected", [
    (17, "INELIGIBLE_AGE"),
    (18, "ELIGIBLE"),
    (65, "ELIGIBLE"),
    (66, "INELIGIBLE_AGE")
])

def test_age_boundary_values(age, expected):
    result = assess_loan(age=age, income=30000, credit_score=700, employed=True)
    assert result == expected, f"expected {expected}, got {result}"