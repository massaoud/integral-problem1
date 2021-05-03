package com.integral.problem1.dto.service;

import com.integral.problem1.dto.DataDTO;
import com.integral.problem1.dto.ResponseDTO;
import com.integral.problem1.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Value("${api.key}")
    private String apiKey;
    @Value("${api.url}")
    private  String apiUrl;

    private RestTemplate restTemplate;

    public WeatherServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(value = "byLocation")
    @Override
    public ResponseDTO getWeatherByLocation(String city, String country) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String url = apiUrl+"?q="+city+","+country+"&appid="+apiKey;
        return new ResponseDTO(200, Constants.REST_STATUS_SUCCESS, new DataDTO<>(Collections.singletonList(restTemplate.exchange(url, HttpMethod.GET, entity, Object.class).getBody())));
    }

    @Cacheable(value = "byCoordinates")
    @Override
    public ResponseDTO getWeatherByCoordinates(String lat, String lon) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String url = apiUrl+"?lat="+lat+"&lon="+lon+"&appid="+apiKey;
        return new ResponseDTO(200,Constants.REST_STATUS_SUCCESS, new DataDTO<>(Collections.singletonList(restTemplate.exchange(url, HttpMethod.GET, entity, Object.class).getBody())));
    }



}
