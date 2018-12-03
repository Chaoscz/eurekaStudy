package com.chaoszc.product.repository;

import com.chaoszc.product.dataobject.ProductCategory;
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
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> types = new ArrayList<>();
        types.add(11);
        types.add(22);
        List<ProductCategory> productCategories = productCategoryRepository.findByCategoryTypeIn(types);
        Assert.assertTrue(productCategories.size()>0);
    }
}