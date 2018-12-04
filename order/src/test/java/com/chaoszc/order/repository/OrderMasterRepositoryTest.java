package com.chaoszc.order.repository;

import com.chaoszc.order.OrderApplicationTests;
import com.chaoszc.order.dataobject.OrderMaster;
import com.chaoszc.order.enums.OrderStatus;
import com.chaoszc.order.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

@Component
public class OrderMasterRepositoryTest extends OrderApplicationTests {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void testSave(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123456");
        orderMaster.setBuyerName("chao");
        orderMaster.setBuyerPhone("12312312312");
        orderMaster.setBuyerAddress("china");
        orderMaster.setBuyerOpenid("110110");
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        orderMaster.setOrderStatus(OrderStatus.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setCreateTime(new Date());
        orderMaster.setUpdateTime(new Date());

        OrderMaster result =  orderMasterRepository.save(orderMaster);
        Assert.assertTrue(result!=null);
    }

}