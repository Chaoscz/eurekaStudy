package com.chaoszc.order.vo;

import lombok.Data;

import javax.persistence.Id;

@Data
public class ResultVO<T> {
    private Integer code;
    private String msg;
    private T data;
}
