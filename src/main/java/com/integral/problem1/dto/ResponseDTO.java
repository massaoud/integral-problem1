package com.integral.problem1.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseDTO<T> implements Serializable {
    int status;
    String message;
    DataDTO data;
}
