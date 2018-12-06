package com.chaoszc.order.service.Impl;

import com.chaoszc.order.client.ProductClient;
import com.chaoszc.order.dataobject.OrderDetail;
import com.chaoszc.order.dataobject.OrderMaster;
import com.chaoszc.order.dataobject.ProductInfo;
import com.chaoszc.order.dto.CartDTO;
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
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId= KeyUtil.genUniqueKey();

        //从详情中获取所有的商品ID
        List<String> productIdList= orderDTO.getDetails().stream()
                .map(OrderDetail::getProductId).collect(Collectors.toList());

        //根据商品ID获取所有商品
        List<ProductInfo> productInfoList =  productClient.listForOrder(productIdList);

        BigDecimal orderAmount = new BigDecimal(0);

        //循环购物车中的商品根据productId 从 商品列表productInfoList中得到单个商品的单价 计算出总价
        for (OrderDetail orderDetail :orderDTO.getDetails()) {
            for (ProductInfo  productInfo: productInfoList) {
                if(orderDetail.getProductId()==productInfo.getProductId()){
                    orderAmount = productInfo.getProductPrice()
                                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                                    .add(orderAmount);
                    BeanUtils.copyProperties(productInfo,orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }

            }

        }
        //扣除库存
        List<CartDTO> cartDTOList = orderDTO.getDetails()
                                        .stream()
                                        .map(e->new CartDTO(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());

        productClient.decreaseStock(cartDTOList);

        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatus.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
