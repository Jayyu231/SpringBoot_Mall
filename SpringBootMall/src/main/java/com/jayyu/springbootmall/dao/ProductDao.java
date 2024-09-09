package com.jayyu.springbootmall.dao;

import com.jayyu.springbootmall.constant.ProductCategory;
import com.jayyu.springbootmall.dto.ProductRequest;
import com.jayyu.springbootmall.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductDao {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest) ;

    void deleteProductById(Integer productId);

    List<Product> getProducts(ProductCategory category, String search);
}
