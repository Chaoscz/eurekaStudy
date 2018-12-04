package com.chaoszc.order.service;

import com.chaoszc.order.dataobject.OrderDetail;
import com.chaoszc.order.dataobject.OrderMaster;
import com.chaoszc.order.dto.OrderDTO;
import org.aspectj.weaver.ast.Or;

public interface OrderService {

    OrderDTO create(OrderDTO orderDTO);

}
