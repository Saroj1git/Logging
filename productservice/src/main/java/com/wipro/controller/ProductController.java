package com.wipro.controller;


import com.wipro.service.ProductService;
import com.wipro.util.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value="/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createProduct(@RequestBody Product product){
       boolean productRecCreated= productService.createProduct(product);
       if(productRecCreated){
           return new ResponseEntity<>("Product Record Created",HttpStatus.CREATED);
       }
        return new ResponseEntity<>("Product Record Not Created",HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.GET,value="/{productId}")
    public Product getProductById(@PathVariable int  productId){

        return  productService.getProduct(productId);
    }


    @RequestMapping(method = RequestMethod.GET)
    public Collection<Product> getProductById(){

        return  productService.getAllProducts();
    }



}
