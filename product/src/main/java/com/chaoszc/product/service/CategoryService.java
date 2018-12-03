package com.chaoszc.product.service;

import com.chaoszc.product.dataobject.ProductCategory;

import java.util.List;

public interface CategoryService {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryType );
}
