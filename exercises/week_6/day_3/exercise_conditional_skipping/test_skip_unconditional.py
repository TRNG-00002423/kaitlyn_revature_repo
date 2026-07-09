import pytest

@pytest.mark.skip(reason="Feature not implemented yet")
def test_future_feature():
    """This test is for a feature we haven't built yet."""
    from myapp import future_feature
    assert future_feature() == "working"

@pytest.mark.skip(reason="Broken after refactoring, fix in JIRA-123")
def test_broken_after_refactor():
    """This test broke and needs investigation."""
    assert False
    