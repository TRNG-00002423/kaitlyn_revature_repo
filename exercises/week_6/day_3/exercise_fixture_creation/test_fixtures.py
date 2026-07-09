import pytest
from doqu import document_base
from doqu import User, DatabaseConnection # models package seemingly was renamed.


@pytest.fixture(scope="session")
def database():
    """
    Database connection shared across all tests in the session.
    Expensive to create, so reuse it.
    """
    print(f"\n[SETUP] Creating database connection")
    db = DatabaseConnection(host="localhost", port=5432)

    yield db

    print(f"\n[TEARDOWN] Closing database connection")
    db.disconnect()

@pytest.fixture(scope="module")
def test_data(database):
    """
    Test data loaded once per module.
    Depends on database fixture.
    """
    print(f"\n[SETUP] Loading test data")
    data = database.load_data("test_data.json") #?

    yield data

    print(f"\n[TEARDOWN] Clearing test data")
    database.clear_test_data()

