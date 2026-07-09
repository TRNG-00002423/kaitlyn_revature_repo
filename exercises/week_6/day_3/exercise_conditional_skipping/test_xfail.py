import sys

import pytest

@pytest.mark.xfail(reason="Known bug, fix in progress.")
def test_known_bug():
    """This test exposes a known bug."""
    # when this starts passing, the xfail will alert us.
    assert 1 + 1 == 2

@pytest.mark.xfail(
    strict = True,
    reason = "This MUST fail, if it passes, then something is wrong."
)
def test_strict_xfail():
    """A test that must always fail."""
    assert False


@pytest.mark.xfail(
    sys.platform == "win32",
    reason = "Flaky on Windows"
)
def test_sometimes_flaky():
    """Test that's flaky on certain platforms."""
    import random
    # simulate flaky behavior
    assert random.choice([True, True, True, True])

@pytest.mark.xfail(raises = ZeroDivisionError)
def test_specific_exception():
    """Expected to raise specific exception."""
    1/0