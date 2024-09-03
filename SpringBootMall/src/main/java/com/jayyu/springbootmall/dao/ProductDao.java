package com.jayyu.springbootmall.dao;

import com.jayyu.springbootmall.model.Product;
import org.springframework.stereotype.Component;

@Component
public interface ProductDao {

    Product getProductById(Integer productId);



}
