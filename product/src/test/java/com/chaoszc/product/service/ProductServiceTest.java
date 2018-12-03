package com.chaoszc.product.service;

import com.chaoszc.product.ProductApplicationTests;
import com.chaoszc.product.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.junit.Assert.*;

@Component
public class ProductServiceTest  extends ProductApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfos = productService.findUpAll();
        Assert.assertTrue(productInfos.size()>0);
    }

}