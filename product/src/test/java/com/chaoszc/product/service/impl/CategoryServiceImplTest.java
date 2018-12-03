package com.chaoszc.product.service.impl;

import com.chaoszc.product.ProductApplicationTests;
import com.chaoszc.product.dataobject.ProductCategory;
import com.chaoszc.product.service.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@Component
public class CategoryServiceImplTest extends ProductApplicationTests {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> types = new ArrayList<>();
        types.add(11);
        types.add(22);
        List<ProductCategory> categories =  categoryService.findByCategoryTypeIn(types);
        Assert.assertTrue(categories.size()>0);
    }
}