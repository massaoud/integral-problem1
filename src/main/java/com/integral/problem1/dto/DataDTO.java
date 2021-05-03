package com.integral.problem1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class DataDTO<T> implements Serializable {
    List<T> content = new ArrayList<>();
}
