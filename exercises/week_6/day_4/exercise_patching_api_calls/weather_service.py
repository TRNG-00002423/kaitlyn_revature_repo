# weather_service.py
import os
import requests


class WeatherAPIError(Exception):
    """Raised when weather API returns an error."""
    pass


class WeatherService:
    """Service for fetching weather data from external API."""
    
    def __init__(self, api_key: str = None):
        self.api_key = api_key or os.environ.get('WEATHER_API_KEY')
        if not self.api_key:
            raise EnvironmentError("WEATHER_API_KEY not set")
        
        self.base_url = os.environ.get(
            'WEATHER_BASE_URL', 
            'https://api.openweathermap.org/data/2.5'
        )
    
    def get_temperature(self, city: str) -> float:
        """Get current temperature for a city."""
        response = requests.get(
            f"{self.base_url}/weather",
            params={"q": city, "appid": self.api_key, "units": "imperial"}
        )
        
        if response.status_code == 401:
            raise WeatherAPIError("Invalid API key")
        if response.status_code != 200:
            raise WeatherAPIError(f"API error: {response.status_code}")
        
        data = response.json()
        return data["main"]["temp"]
    
    def get_forecast(self, city: str, days: int = 5) -> list:
        """Get weather forecast for a city."""
        response = requests.get(
            f"{self.base_url}/forecast",
            params={"q": city, "appid": self.api_key, "cnt": days * 8}
        )
        
        if response.status_code != 200:
            raise WeatherAPIError(f"API error: {response.status_code}")
        
        data = response.json()
        return [{"date": item["dt_txt"], "temp": item["main"]["temp"]} 
                for item in data["list"]]
    
    def post_forecast(self, city, temp):
        """
        Post weather forecast for a city.
        I implemented this to test mock_post.
        """
        response = requests.post({"main": {city: temp}})
        return response
    
    def validate_city(city: str):
        """
        Not implemented.
        """
        return False