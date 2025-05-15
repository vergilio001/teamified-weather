package com.example.weather.Controller;

import com.example.weather.DTO.WeatherDTO;
import com.example.weather.Services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private CacheManager cacheManager;

    @GetMapping("/weather")
    public ResponseEntity<Map<String, Object>> getWeatherDetailsByCity(@RequestParam String city) {
        Map<String, Object> response = new HashMap<>();
        try {
            Cache cache = cacheManager.getCache("weatherCache");
            if(cache.get(city) == null) {
                response.put("response", weatherService.getWeatherDetails(city));
            }else{
                WeatherDTO cachedWeather = cache.get(city, WeatherDTO.class);
                response.put("response", cachedWeather);
                System.out.println("from cache");
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), e.getMessage());
            problemDetail.setProperty("description", "Unexpected Error upon getting weather details");
            response.put("problemDetail", problemDetail);
            response.put("status", "failed");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
