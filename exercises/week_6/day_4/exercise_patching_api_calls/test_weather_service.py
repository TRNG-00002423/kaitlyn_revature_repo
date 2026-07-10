import pytest
from unittest.mock import patch, MagicMock
from weather_service import WeatherService, WeatherAPIError
import os

class TestWeatherServiceWithDecorator:

    @patch('weather_service.requests.get')
    def test_temperature_returns_value(self, mock_get): # self is required this time around.
        """Test successful temperature fetch."""
        # configure mock response
        mock_response = MagicMock()
        mock_response.status_code = 200
        mock_response.json.return_value = {
            "main": {"temp": 72.5},
            "name": "New York"
        }
        mock_get.return_value = mock_response

        service = WeatherService(api_key="fake_key")
        temp = service.get_temperature("New York")

        assert temp == 72.5
        mock_get.assert_called_once()

    @patch('weather_service.requests.get')
    def test_get_temperature_handles_api_error(self, mock_get):
        """Test error handling of API response."""
        mock_response = MagicMock()
        mock_response.status_code = 401
        mock_response.json.return_value = {"error": "Invalid API key"}
        mock_get.return_value = mock_response

        service = WeatherService(api_key="invalid_key")

        with pytest.raises(WeatherAPIError, match="API key"):
            service.get_temperature("New York")

    def test_forecast_with_context_manager(self):
        """Use context manager for more control."""
        with patch('weather_service.requests.get') as mock_get:
            mock_response = MagicMock()
            mock_response.status_code = 200
            mock_response.json.return_value = {
                "list": [
                    {"dt_txt": "2024-01-01 12:00:00", "main": {"temp": 65}},
                    {"dt_txt": "2024-01-02 12:00:00", "main": {"temp": 68}}
                ]
            }
            mock_get.return_value = mock_response
            service = WeatherService(api_key="fake_key")
            forecast = service.get_forecast(city="Chicago", days=2)

            assert len(forecast) == 2
            assert forecast[0]["temp"] == 65
            mock_get.assert_called_once()

    def test_partial_matching_with_context_manager(self):
        """Patch only a specific part of the test."""
        service = WeatherService(api_key="fake_key")

        # first call - not patched. would fail in a real-life scenario.
        # service.get_temperature("NYC") # real call!

        # patched session
        with patch('weather_service.requests.get') as mock_get:
            mock_get.return_value = MagicMock(
                status_code = 200,
                json = lambda: {"main": {"temp": 70}}
            )
            temp = service.get_temperature("NYC")
            assert temp == 70
        # after context - no longer patched

    @patch('weather_service.requests.get')
    def test_correct_path_location(self, mock_get):
        """Patch where the function is looked up"""
        mock_get.return_value = MagicMock (
            status_code = 200,
            json = lambda: {"main": {"temp": 75}}
        )
        service = WeatherService(api_key="fake_key")
        temp = service.get_temperature("city")
        assert temp == 75

    def test_patch_multiple_imports(self):
        """When module has multiple imports."""
        with patch('weather_service.requests.get') as mock_get:
            with patch('weather_service.requests.post') as mock_post:
                mock_get.return_value = MagicMock(status_code = 200, json = lambda: {"main": {"temp": 70}})
                mock_post.return_value = MagicMock(status_code = 200, json = lambda: {"message": "temperature posted successfully"})
                service = WeatherService(api_key="key")

                temp = service.get_temperature("some city")
                assert temp == 70

                response = service.post_forecast("Boston", 60)
                assert response
                assert "temperature posted successfully" == response.json()["message"]
                mock_get.assert_called_once()
                mock_post.assert_called_once()

    # def test_patch_object_method(self):
    #     """Patch a specific method on an object."""
    #     service = WeatherService(api_key="key")

    #     with patch.object(service, '_make_request') as mock_request:
    #         mock_request.return_value = {"main": {"temp": 80}}
    #         temp = service.get_temperature("Miami")
    #         assert temp == 80
    #         mock_request.assert_called_once()

    def test_patch_class_method(self):
        with patch.object(WeatherService, 'validate_city', return_value = True):
            service = WeatherService(api_key="key")
            # mocked validate_city will always return True regardless of input
            assert service.validate_city("Fake city")

    def test_service_uses_env_api_key(self):
        """Test service reads API key from environment."""
        with patch.dict(os.environ, {'WEATHER_API_KEY': 'env_key'}):
            service = WeatherService()
            assert service.api_key == "env_key"

    def test_service_with_custom_env(self):
        """Test with complete custom environment."""
        custom_env = {
            'WEATHER_API_KEY': 'test_key',
            'WEATHER_BASE_URL': 'http://test.api.com',
            'WEATHER_TIMEOUT': '30'
        }
        with patch.dict(os.environ, custom_env, clear=True):
            service = WeatherService()
            assert service.api_key == 'test_key'
            assert service.base_url == 'http://test.api.com'

    def test_service_missing_env_raises_error(self):
        """Raises error when required env var missing."""
        with patch.dict(os.environ, {}, clear=True):
            with pytest.raises(EnvironmentError, match="API_KEY"):
                WeatherService()