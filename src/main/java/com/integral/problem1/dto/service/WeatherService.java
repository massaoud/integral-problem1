package com.integral.problem1.dto.service;

import com.integral.problem1.dto.ResponseDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestParam;

public interface WeatherService {
    ResponseDTO getWeatherByLocation(String city, String country);
    ResponseDTO getWeatherByCoordinates(String lat,String lon);
}
