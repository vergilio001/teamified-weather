package com.example.weather.Services;

import com.example.weather.DTO.WeatherDTO;
import com.example.weather.Repository.WeatherRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements WeatherService {
    @Autowired
    private WeatherRepository weatherRepository;


    @Override
    @Cacheable(value = "weatherCache", key = "#city")
    public WeatherDTO getWeatherDetails(String city) throws JsonProcessingException {
        return weatherRepository.getWeatherDetails(city);
    }

}
