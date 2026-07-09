import pytest
from doqu import User, DatabaseConnection # models package seemingly was renamed.

@pytest.fixture
def user():
    """Create a standard user."""
    return User(
        id=1,
        username="testuser",
        email="test@example.com",
        role = "user"
    )

@pytest.fixture
def admin_user():
    """Create an admin test user."""
    return User(
        id=99,
        username="admin",
        email="admin@example.com",
        role="admin"
    )