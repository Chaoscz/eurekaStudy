package com.chaoszc.product.repository;

import com.chaoszc.product.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void findByProductStatus() {
       List<ProductInfo> productInfos =  productInfoRepository.findByProductStatus(0);
        Assert.assertTrue(productInfos.size()>0);
    }

    @Test
    public void findByProductIdIn() {
        List<String> types = new ArrayList<>();
        types.add("157875196366160022");
        types.add("157875227953464068");
        List<ProductInfo> productCategories = productInfoRepository.findByProductIdIn(types);
        Assert.assertTrue(productCategories.size()>0);
    }

}
