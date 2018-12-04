package com.chaoszc.order.dto;

import com.chaoszc.order.dataobject.OrderDetail;
import lombok.Data;

import javax.validation.constraints.Max;
import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDTO {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus;

    private Integer payStatus;

    private List<OrderDetail> details;
}
