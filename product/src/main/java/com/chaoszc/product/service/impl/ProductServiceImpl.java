package com.chaoszc.product.service.impl;

import com.chaoszc.product.dto.CartDTO;
import com.chaoszc.product.dataobject.ProductInfo;
import com.chaoszc.product.enums.ProductStatusEnum;
import com.chaoszc.product.enums.ResultEnum;
import com.chaoszc.product.exception.ProductException;
import com.chaoszc.product.repository.ProductInfoRepository;
import com.chaoszc.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findList(List<String> productList) {
        return productInfoRepository.findByProductIdIn(productList);
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO :cartDTOList) {
            Optional<ProductInfo> productInfoOptional =  productInfoRepository.findById(cartDTO.getProductId());
            if(!productInfoOptional.isPresent()){
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo = productInfoOptional.get();
            Integer result =  productInfo.getProductStock() - cartDTO.getProductQuantity();
            if(result<0){
                throw  new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }

    }
}
