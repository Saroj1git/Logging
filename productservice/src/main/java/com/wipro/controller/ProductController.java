package com.wipro.controller;


import com.wipro.advice.TrackExecutionTime;
import com.wipro.service.ProductService;
import com.wipro.util.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collection;

@RestController
@RequestMapping(value="/product")
@EnableSwagger2
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.POST)
    @TrackExecutionTime
    public ResponseEntity createProduct(@RequestBody Product product){
       boolean productRecCreated= productService.createProduct(product);
       if(productRecCreated){
           return new ResponseEntity<>("Product Record Created",HttpStatus.CREATED);
       }
        return new ResponseEntity<>("Product Record Not Created",HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.GET,value="/{productId}")
    @TrackExecutionTime
    public Product getProductById(@PathVariable int  productId){

        return  productService.getProduct(productId);
    }


    @RequestMapping(method = RequestMethod.GET)
    @TrackExecutionTime
    public Collection<Product> getProductById(){

        return  productService.getAllProducts();
    }



}
