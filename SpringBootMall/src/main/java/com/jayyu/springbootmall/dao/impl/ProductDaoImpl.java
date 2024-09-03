package com.jayyu.springbootmall.dao.impl;

import com.jayyu.springbootmall.dao.ProductDao;
import com.jayyu.springbootmall.model.Product;
import com.jayyu.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Product getProductById(Integer productId) {
        String sql = "select product_id ,product_name, category, image_url, price, stock," +
                " description, create_date, last_modified_date " +
                "from product where product_id = :productId";
        HashMap<String,Object> map = new HashMap<>();
        map.put("productId", productId);

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        if (productList.size() > 0 ) {
            return productList.get(0);
        }  else {
            return null;
        }

    }
}
