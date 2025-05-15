package com.example.weather.Repository;

import com.example.weather.DTO.WeatherDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface WeatherRepository {
    WeatherDTO getWeatherDetails (String city) throws JsonProcessingException;

}
