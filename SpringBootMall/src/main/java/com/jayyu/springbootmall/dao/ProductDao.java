package com.jayyu.springbootmall.dao;

import com.jayyu.springbootmall.dto.ProductRequest;
import com.jayyu.springbootmall.model.Product;
import org.springframework.stereotype.Component;

@Component
public interface ProductDao {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest) ;

    void deleteProductById(Integer productId);
}
