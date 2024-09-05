package com.jayyu.springbootmall.controller;

//import com.jayyu.springbootmall.service.ProductService;
import com.jayyu.springbootmall.dao.ProductDao;
import com.jayyu.springbootmall.dto.ProductRequest;
import com.jayyu.springbootmall.model.Product;
import com.jayyu.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDao productDao;

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
       Product product = productService.getProductById(productId);

       if (product != null){
           return ResponseEntity.status(HttpStatus.OK).body(product);
       }
       else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
    }

    // Q:這個功能的目的是?   從前端將 json 檔案匯入到後端的這個端口(endpoint)後，把回傳格式以Product 傳回 A:Yes
    //  不過，沒辦法一次到位，要先取得 productId, 再取得 product
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest){
        Integer productId =  productService.createProduct(productRequest);
        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);

    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable  Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest){

        // 先確認是否productId 存在與否?  先進去資料庫內看productId是否存在?
        Product productInDB = productService.getProductById(productId);

        // 如果 productId 不存在的話，就回傳 404 not Found(網頁不存在)
        if (productInDB  == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // 如果 productId 存在的話，就更新商品，回傳200，並且更新新的Product。
        productService.updateProduct(productId, productRequest);
        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.OK).body(product);
    }


}
