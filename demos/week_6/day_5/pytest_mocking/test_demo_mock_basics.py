# pytest - mock basics
# pytest-mock provides the "mocker" fixture

# install with pip install pytest-mock
import pytest 
import os
from services import UserService, User, UserRepository, EmailClient

def test_mocker_creates_mocks(mocker):
    """
    The mocker fixture creates mock objects.

    mocker.Mock() creates a basic mock.
    mocker.magicMock() creates a mock with magic methods.
    """
    mock_func = mocker.Mock()
    mock_func.return_value = 42

    result = mock_func()
    assert result == 42
    mock_func.assert_called_once()

def test_mocker_mock_with_spec(mocker):
    """
    Use spec to make sure mocker has same interface as real object.
    Catches typos in method names.
    """
    mock_repo = mocker.Mock(spec = UserRepository)

    mock_repo.find_by_id.return_value = User(1, "John", "john@example.com")

    # mock_repo.find_by_idd.return_value = User(1, "John", "john@example.com")
    # ^ if this was uncommented, the test would fail!
    user = mock_repo.find_by_id(1)
    assert user.name == "John"

def test_mocker_patch(mocker):
    """
    mocker.patch() replaces objects during a test
    automatically cleaned up after test
    """

    mock_exists = mocker.patch('os.path.exists', return_value = True)
    assert os.path.exists('any/path/at/all/sustingus') is True
    assert os.path.exists('obviously/fake/path') is True

    mock_exists.assert_called()

def test_mocker_patch_dict(mocker):
    """
    mocker.path.dict() patches dictionary contents.
    great for environmental variables!
    """
    mocker.patch.dict(os.environ, {
        'API_KEY' : 'test-key-123',
        'DEBUG': 'true'
    })

    assert os.environ['API_KEY'] == 'test-key-123'

def test_mocker_patch_object(mocker):
    """
    mocker.patch.object() patches a specific method on an object.
    """

    user = User(1, "original", "original@test.com")

    mocker.patch.object(user, 'email', 'patches@test.com')
    assert user.email == 'patches@test.com' # patched
    assert user.name == 'original' # not patched

# testing with mocked dependencies
def test_user_service_with_mock_repo(mocker):
    """
    Create UserService with mock repository
    """
    # create the mock repository
    mock_repo = mocker.Mock(spec=UserRepository)
    mock_repo.find_by_id.return_value = User(1, "John", "john@example.com")

    # create a user service
    service = UserService(repository = mock_repo)

    # test
    user = service.get_user(1) 
    assert user.name == "John"
    mock_repo.find_by_id.assert_called_once_with(1)

def test_user_service_create_user(mocker):
    """
    Test user creation with mocked repo and email client.
    """
    mock_repo = mocker.Mock(spec = UserRepository)
    mock_email = mocker.Mock(spec = EmailClient)

    # set up behavior
    mock_repo.find_by_email.return_value = None # no existing user
    mock_repo.save.side_effect = lambda u: User(id = 100, name = u.name, email = u.email)

    mock_email.send.return_value = True

    # create service and test
    service = UserService(repository=mock_repo, email_client=mock_email)
    user = service.create_user("Alice", "alice@test.com")

    # verify
    assert user.id == 100
    assert user.name == "Alice"
    mock_repo.save.assert_called_once()
    mock_email.send.assert_called_once()

# mocker.Spy() tracks real function calls
def test_mocker_spy(mocker):
    """
    Spy wraps a real function to track calls.
    The function still executes, but you can verify it was called.
    """

    # create mocks
    mock_repo = mocker.Mock(spec=UserRepository)
    mock_email = mocker.Mock(spec=EmailClient)

    service = UserService(mock_repo, mock_email)
    user = User(id = 1, name = "Alice", email = "alice@example.com")

    mock_repo.find_by_id.return_value = user
    mock_repo.save.return_value = user

    spy = mocker.spy(service, "get_user")

    result = service.deactivate_user(1)
    assert result.active is False
    spy.assert_called_once_with(1)

    mock_repo.save.assert_called_once_with(user)
    mock_email.send.assert_called_once()

# combine mocker with pytest fixtures
@pytest.fixture
def mock_repository(mocker):
    """Fixture that provides a configured mock repository"""
    mock = mocker.Mock(spec=UserRepository)
    mock.find_by_id.return_value = User(1, "Test User", "test@example.com")
    mock.find_by_email.return_value = None
    mock.save.side_effect = lambda u: User(id = u.id, name = u.name, email = u.email)
    return mock

@pytest.fixture
def mock_email_client(mocker):
    """Fixture that provides a mock email client."""
    mock = mocker.Mock(spec = EmailClient)
    mock.send.return_value = True
    return mock

@pytest.fixture
def user_service(mock_repository, mock_email_client):
    """UserService with all dependencies mocked."""
    return UserService(repository = mock_repository, email_client = mock_email_client)

def test_with_fixtures(user_service, mock_repository):
    """Use the fixture-provided service and mocks."""
    user = user_service.get_user(1)
    assert user.name == "Test User"
    mock_repository.find_by_id.assert_called_once_with(1)

def test_create_user_with_fixture(user_service, mock_email_client):
    """Test user creation with fixtures."""
    user = user_service.create_user("New User", "new@example.com")
    assert user.name == "New User"
    mock_email_client.send.assert_called_once()