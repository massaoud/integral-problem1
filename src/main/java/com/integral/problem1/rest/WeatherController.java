package com.integral.problem1.rest;

import com.integral.problem1.dto.ResponseDTO;
import com.integral.problem1.dto.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/weather")
public class WeatherController {

private WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping(value = "/byLocation")
    public ResponseDTO getWeatherByLocation(
            @RequestParam(name ="city" ,required = true) String city,
            @RequestParam(name ="country" ,required = true) String country
    ) {
        log.info("call byCoordinates api with city {} and country {}", city,country);
       return weatherService.getWeatherByLocation(city,country);
    }

    @GetMapping(value = "/byCoordinates")
    public Object getWeatherByCoordinates(
            @RequestParam(name ="lat" ,required = true) String lat,
            @RequestParam(name ="lon" ,required = true) String lon
    ) {
        log.info("call byCoordinates api with latitude {} and longitude {}", lat,lon);
        return weatherService.getWeatherByCoordinates(lat,lon);
     }

}
