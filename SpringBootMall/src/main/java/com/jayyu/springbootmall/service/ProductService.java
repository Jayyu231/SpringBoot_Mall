package com.jayyu.springbootmall.service;

import com.jayyu.springbootmall.constant.ProductCategory;
import com.jayyu.springbootmall.dto.ProductRequest;
import com.jayyu.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);

    List<Product> getProducts(ProductCategory category, String search);
}
