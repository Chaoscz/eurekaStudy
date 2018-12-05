package com.chaoszc.product.service.impl;

import com.chaoszc.product.dto.CartDTO;
import com.chaoszc.product.ProductApplicationTests;
import com.chaoszc.product.service.ProductService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ProductServiceImplTest extends ProductApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    public void findUpAll() {
    }

    @Test
    public void findList() {
    }


    @Test
    public void decreaseStock() {
        CartDTO cartDTO = new CartDTO("157875196366160022",2);

        productService.decreaseStock(Arrays.asList(cartDTO));
    }
}