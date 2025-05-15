Teamified Weather
A Spring Boot application that retrieves temperature and windspeed for any city in the world using the WeatherStack API. The app uses Caffeine Cache for efficient caching, ensuring faster responses.
Features
- 🌍 Get real-time weather data for any city worldwide.
- ⚡ Fast retrieval of cached weather data using Caffeine.
- 🏗 Built with Java 21 and Spring Boot for scalability.
Tech Stack
- Backend: Spring Boot, Java 21
- Caching: Caffeine Cache
- External API: WeatherStack
Setup & Installation
1. Clone the repository
git clone https://github.com/vergilio001/teamified-weather.git
cd teamified-weather


2. Configure API Key
Get your API key from WeatherStack and add it to your application properties:
weatherstack.api.key=YOUR_API_KEY


3. Build and Run
./mvnw spring-boot:run



4. Test Endpoints
Get Weather Data
curl -X GET "http://localhost:8080/weather?city=Singapore"


Expected response:
{
    "temperature": 30,
    "windspeed": 15
}


Caching Mechanism
- Uses Caffeine Cache to store weather responses for fast access.
- Cache expiration can be configured in the CacheConfig.java file.

