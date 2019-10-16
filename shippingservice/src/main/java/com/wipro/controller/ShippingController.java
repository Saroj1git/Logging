package com.wipro.controller;
import com.wipro.service.ShippingService;

import com.wipro.util.ShippingDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value="/shippingdetails")
public class ShippingController {

    @Autowired
    private ShippingService shippingService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createShippingRecord(@RequestBody ShippingDetails shippingDetails){
       boolean productRecCreated= shippingService.createShippingRecord(shippingDetails);
       if(productRecCreated){
           return new ResponseEntity<>("Shipping  Record Created",HttpStatus.CREATED);
       }
        return new ResponseEntity<>("Shipping Record Not Created",HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.GET,value="/{productId}")
    public ShippingDetails getShippingDetailsById(@PathVariable int  productId){

        return  shippingService.getShippingDetails(productId);
    }


    @RequestMapping(method = RequestMethod.GET)
    public Collection<ShippingDetails> getAllShippingDetails(){

        return  shippingService.getAllShippingDetails();
    }



}
