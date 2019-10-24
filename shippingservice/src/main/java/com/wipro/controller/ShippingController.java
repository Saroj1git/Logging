package com.wipro.controller;
import com.wipro.advice.TrackExecutionTime;
import com.wipro.service.ShippingService;

import com.wipro.util.ShippingDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collection;

@RestController
@RequestMapping(value="/shippingdetails")
@EnableSwagger2
public class ShippingController {

    @Autowired
    private ShippingService shippingService;

    @RequestMapping(method = RequestMethod.POST)
    @TrackExecutionTime
    public ResponseEntity createShippingRecord(@RequestBody ShippingDetails shippingDetails){
       boolean productRecCreated= shippingService.createShippingRecord(shippingDetails);
       if(productRecCreated){
           return new ResponseEntity<>("Shipping  Record Created",HttpStatus.CREATED);
       }
        return new ResponseEntity<>("Shipping Record Not Created",HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.GET,value="/{productId}")
    @TrackExecutionTime
    public ShippingDetails getShippingDetailsById(@PathVariable int  productId){

        return  shippingService.getShippingDetails(productId);
    }


    @RequestMapping(method = RequestMethod.GET)
    @TrackExecutionTime
    public Collection<ShippingDetails> getAllShippingDetails(){

        return  shippingService.getAllShippingDetails();
    }



}
