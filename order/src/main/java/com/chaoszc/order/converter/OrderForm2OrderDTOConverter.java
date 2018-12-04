package com.chaoszc.order.converter;

import com.chaoszc.order.dataobject.OrderDetail;
import com.chaoszc.order.dto.OrderDTO;
import com.chaoszc.order.enums.ResultEnum;
import com.chaoszc.order.exception.OrderException;
import com.chaoszc.order.form.OrderForm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderForm2OrderDTOConverter {
    public static OrderDTO convert(OrderForm orderForm){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenId());
        List<OrderDetail> details = new ArrayList<>();
        Gson gson = new Gson();
        try {
            details = gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            log.error("【json转换错误】,String={}",orderForm.getItems());
            throw  new OrderException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setDetails(details);
        return orderDTO;
    }
}
