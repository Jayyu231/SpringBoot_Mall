package com.jayyu.springbootmall.rowmapper;

import com.jayyu.springbootmall.constant.ProductCategory;
import com.jayyu.springbootmall.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();

        product.setProductId(resultSet.getInt("product_id"));
        product.setProductName(resultSet.getString("product_name"));


        String categoryStr = resultSet.getString("category");
        ProductCategory productCategory = ProductCategory.valueOf(categoryStr);
        product.setCategory(productCategory);

        // 一行寫法:   將String 轉換成 ProductCategory 的enum 值
//        product.setCategory(ProductCategory.valueOf(resultSet.getString("category")));;


        product.setImage_url(resultSet.getString("image_url"));;
        product.setPrice(resultSet.getInt("price"));
        product.setStock(resultSet.getInt("stock"));
        product.setDescription(resultSet.getString("description"));;

        product.setCreate_date(resultSet.getTimestamp("create_date"));
        product.setLast_modified_date(resultSet.getTimestamp("last_modified_date"));

        return product;
    }
}
