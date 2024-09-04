package com.jayyu.springbootmall.service;

import com.jayyu.springbootmall.dto.ProductRequest;
import com.jayyu.springbootmall.model.Product;

public interface ProductService {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

}
