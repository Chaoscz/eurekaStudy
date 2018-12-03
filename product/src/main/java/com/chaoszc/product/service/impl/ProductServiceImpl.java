package com.chaoszc.product.service.impl;

import com.chaoszc.product.dataobject.ProductInfo;
import com.chaoszc.product.enums.ProductStatusEnum;
import com.chaoszc.product.repository.ProductInfoRepository;
import com.chaoszc.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }
}
