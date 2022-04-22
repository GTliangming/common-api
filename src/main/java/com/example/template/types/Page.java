package com.example.template.types;

import lombok.Data;

import java.util.List;

@Data
public class Page<T>{
    private Integer limit;
    private Integer offser;
    private Integer total;
    private List<T> data;
}
