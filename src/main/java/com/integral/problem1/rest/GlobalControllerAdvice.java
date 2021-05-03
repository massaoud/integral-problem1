package com.integral.problem1.rest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.integral.problem1.dto.DataDTO;
import com.integral.problem1.dto.ErrorDTO;
import com.integral.problem1.dto.ResponseDTO;
import com.integral.problem1.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalControllerAdvice {//extends ResponseEntityExceptionHandler {
    
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = HttpServerErrorException.class)
    public @ResponseBody
    ResponseDTO handleException(HttpServerErrorException e) {
        log.error("Global HttpServerErrorException Exception", e);
        ObjectMapper mapper = new ObjectMapper();
        try {
            ErrorDTO errorDTO = mapper.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
            List dataItems = new ArrayList<>();
           return new ResponseDTO(e.getStatusCode().value(),Constants.REST_STATUS_ERROR, new DataDTO<ErrorDTO>(Collections.singletonList(errorDTO)));

        } catch (JsonProcessingException jsonProcessingException) {
            jsonProcessingException.printStackTrace();
        }
        return null;
    }

    @ExceptionHandler(value = HttpClientErrorException.class)
    public @ResponseBody
    ResponseDTO handleException(HttpClientErrorException e) {
        log.error("Global HttpServerErrorException Exception", e);
        ObjectMapper mapper = new ObjectMapper();
        try {
            ErrorDTO errorDTO = mapper.readValue(e.getResponseBodyAsString(), ErrorDTO.class);
            List dataItems = new ArrayList<>();
            return new ResponseDTO(e.getStatusCode().value(),Constants.REST_STATUS_ERROR, new DataDTO<ErrorDTO>(Collections.singletonList(errorDTO)));

        } catch (JsonProcessingException jsonProcessingException) {
            jsonProcessingException.printStackTrace();
        }
        return null;
    }

    @ExceptionHandler(value = ServletException.class)
    public @ResponseBody
    ResponseDTO handleException(ServletException e) {
        log.error("Global ServletException Exception", e);
        ObjectMapper mapper = new ObjectMapper();
            ErrorDTO errorDTO = ErrorDTO.builder().cod("400").message( e.getMessage()).build();
            List dataItems = new ArrayList<>();
            return new ResponseDTO(400,Constants.REST_STATUS_ERROR, new DataDTO<ErrorDTO>(Collections.singletonList(errorDTO)));
    }

}
