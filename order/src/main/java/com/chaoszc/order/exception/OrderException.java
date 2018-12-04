package com.chaoszc.order.exception;

import com.chaoszc.order.enums.ResultEnum;

import javax.persistence.criteria.CriteriaBuilder;

public class OrderException extends RuntimeException {
    private Integer code;

    public OrderException(Integer code,String msg){
        super(msg);
        this.code =code;
    }
    public OrderException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code =resultEnum.getCode();
    }
}
