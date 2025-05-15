package com.example.weather.Services;

import com.example.weather.DTO.WeatherDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface WeatherService {

    WeatherDTO getWeatherDetails (String city) throws JsonProcessingException;

}
