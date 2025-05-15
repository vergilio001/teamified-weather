package com.example.weather.Repository;

import com.example.weather.DTO.WeatherDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;


@Repository
public class WeatherRepositoryImpl implements WeatherRepository {
    @Value("${weatherstack.privatekey}")
    private String key;

    @Override
    public WeatherDTO getWeatherDetails(String city) throws JsonProcessingException {
        String weatherStackUrl = "http://api.weatherstack.com/current?access_key=:Key&query=:City";
        String failOverUrl = "http://api.openweathermap.org/data/2.5/weather?q=melbourne,AU&appid=2326504fb9b100bee21400190e4dbe6d";
        String response = null;
        RestTemplate restTemplate = new RestTemplate();
        WeatherDTO weatherDTO = new WeatherDTO();
        try {
            response = restTemplate.getForObject(weatherStackUrl.replace(":Key", key).replace(":City", city), String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);
            String windSpeed = rootNode.path("current").path("wind_speed").toString();
            String temperature = rootNode.path("current").path("temperature").toString() + "°C";
            weatherDTO.setWind_speed(windSpeed);
            weatherDTO.setTemperature_degrees(temperature);

        } catch (Exception e) {
            response = restTemplate.getForObject(failOverUrl, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);
            String windSpeed = rootNode.path("wind").path("speed").toString();
            double temperatureDouble = rootNode.path("main").path("temp").asDouble();
            weatherDTO.setWind_speed(windSpeed);
            weatherDTO.setTemperature_degrees(String.valueOf((temperatureDouble - 273.15)) + "°C");

        }

        return weatherDTO;


    }
}
