import sys
import pytest

@pytest.mark.skipif(
    sys.version_info < (3, 10),
    reason = "Requires Python 3.10 for match statement"
)
def test_match_statement():
    """Test using Python 3.10+ match statement."""
    value = "test"
    match value:
        case "test":
            result = True
        case _:
            result = False
    assert result

@pytest.mark.skipif(
    sys.version_info >= (3, 12),
    reason = "Deprecated in Python 3.12."
)
def test_deprecated_feature():
    """Test for deprecated feature in newer Python."""
    assert True

requires_python_3_10 = pytest.mark.skipif(
    sys.version_info < (3, 10),
    reason = "Requires Python 3.10+"
)

@requires_python_3_10
def test_another_310_feature():
    assert True