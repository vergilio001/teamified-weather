package com.example.weather.DTO;

public class WeatherDTO {
    private String wind_speed ;
    private String temperature_degrees;

    public String getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(String wind_speed) {
        this.wind_speed = wind_speed;
    }

    public String getTemperature_degrees() {
        return temperature_degrees;
    }

    public void setTemperature_degrees(String temperature_degrees) {
        this.temperature_degrees = temperature_degrees;
    }
}
