package com.jayyu.springbootmall.dao.impl;

import com.jayyu.springbootmall.constant.ProductCategory;
import com.jayyu.springbootmall.dao.ProductDao;
import com.jayyu.springbootmall.dto.ProductRequest;
import com.jayyu.springbootmall.model.Product;
import com.jayyu.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        //   可否把 productRequest 轉換為 product 呢?  A: 可以，需要以下兩點
            //  少了 productId、 create_date、last_modified_date
            //  必須加上原本的前端傳來的Object productRequest 進入 database 再加上方的東西

        Map<String, Object> map = new HashMap<>();
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        // 注意: 必須要轉換成 toString
//        map.put("category", productRequest.getCategory());
        map.put("imageUrl", productRequest.getImage_url());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        Date date = new Date();
        map.put("createdDate", date);
        map.put("lastModifiedDate", date);

        String sql = "Insert into product(product_name, category, image_url, price, stock, description, " +
                    "create_date, last_modified_date)" +
                "Values (:productName, :category, :imageUrl, :price, :stock, :description, " +
                ":createdDate, :lastModifiedDate)";

        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), generatedKeyHolder);

        int genProductId = generatedKeyHolder.getKey().intValue();

        return genProductId;
    }

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
