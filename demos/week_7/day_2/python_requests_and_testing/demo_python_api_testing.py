"""
INSTALLATION:
pip install pytest requests pytest-html

RUN TESTS:
pytest demo_python_api_testing.py -v
pytest demo_python_api_testing.py -v --html=report.html
"""

import pytest
import requests

@pytest.fixture(scope="module")
def base_url():
    """
    Fixtures provide reusable test data/configuration.
    scope = "module" means that it's created once per test file.
    """
    return "https://jsonplaceholder.typicode.com"

@pytest.fixture(scope="module")
def session():
    """
    Session fixture maintains connection pool.
    More efficient for multiple requests.
    """
    sess = requests.Session()
    sess.headers.update({
        "Accept": "application/json",
        "Content-Type": "application/json"
    })
    yield sess
    sess.close()

@pytest.fixture
def sample_post():
    """
    Fixture for test data.
    Can be overwritten or parametrized.
    """
    return {
        "title": "Test Title",
        "body": "Test Body Content",
        "userId": 1
    }

class TestBasicRequests:
    """
    Group related tests in classes.
    Pytest discovers tests by name ("test_" or "_test")
    """

    def test_get_single_post(self, base_url, session):
        response = requests.get(f"{base_url}/posts/1")
        assert response.status_code == 200
        assert response.headers['Content-Type'] =="application/json; charset=utf-8"

        data = response.json()
        assert data["id"] == 1
        assert data["userId"] == 1