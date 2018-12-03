package com.chaoszc.product.service;

import com.chaoszc.product.dataobject.ProductInfo;

import java.util.List;

public interface ProductService{
    /**
     * 查询所有在架商品列表
     */
    List<ProductInfo> findUpAll();
}
