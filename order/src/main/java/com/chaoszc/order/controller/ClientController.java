package com.chaoszc.order.controller;

import com.chaoszc.order.client.ProductClient;
import com.chaoszc.order.dataobject.ProductInfo;
import com.chaoszc.order.dto.CartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClientController {

    /*@Autowired
    private LoadBalancerClient loadBalancerClient;*/

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private ProductClient productClient;



    @GetMapping("/getProductMsg")
    public String getProductMsg(){
        //第一种方式 直接使用 RestTemplate url 写死
        //RestTemplate restTemplate = new RestTemplate();
        //String msg = restTemplate.getForObject("http://127.0.0.1:8080/msg",String.class);

         //第二种方式 利用loadBalancerClient spring.application.name 获取 信息 在使用RestTemplate
//        ServiceInstance serviceInstance =  loadBalancerClient.choose("PRODUCT");
//        String url =    String.format("http://%s:%s",serviceInstance.getHost(),serviceInstance.getPort())+"/msg";
//        RestTemplate restTemplate = new RestTemplate();
//        String msg = restTemplate.getForObject(url,String.class);

        //第三种方式 创建config 并使用@LoadBalanced 创建 RestTemplate 的bean
       // String msg =  restTemplate.getForObject("http://PRODUCT/msg",String.class);

        String msg = productClient.productMsg();

        return msg;
    }

    @GetMapping("/getProductList")
    public String getProductList(){


        List<ProductInfo> msg = productClient.listForOrder(Arrays.asList("164103465734242707"));

        return "111";
    }

    @GetMapping("/decreaseStock")
    public String decreaseStock(){

        CartDTO cartDTO = new CartDTO("164103465734242707",3);

        productClient.decreaseStock(Arrays.asList(cartDTO));

        return "111";
    }
}
