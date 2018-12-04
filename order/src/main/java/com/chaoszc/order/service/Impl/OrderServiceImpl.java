package com.chaoszc.order.service.Impl;

import com.chaoszc.order.dataobject.OrderDetail;
import com.chaoszc.order.dataobject.OrderMaster;
import com.chaoszc.order.dto.OrderDTO;
import com.chaoszc.order.enums.OrderStatus;
import com.chaoszc.order.enums.PayStatusEnum;
import com.chaoszc.order.repository.OrderDetailRepository;
import com.chaoszc.order.repository.OrderMasterRepository;
import com.chaoszc.order.service.OrderService;
import com.chaoszc.order.utils.KeyUtil;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {


        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(KeyUtil.genUniqueKey());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(5));
        orderMaster.setOrderStatus(OrderStatus.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
