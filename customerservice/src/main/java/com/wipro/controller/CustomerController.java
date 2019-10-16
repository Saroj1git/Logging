package com.wipro.controller;


import com.wipro.service.CustomerService;
import com.wipro.util.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService custService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createCustomer(@RequestBody Customer cust){
       boolean custRecCreated= custService.createCustomer(cust);
       if(custRecCreated){
           return new ResponseEntity<>("Customer Record Created",HttpStatus.CREATED);
       }
        return new ResponseEntity<>("Customer Record Not Created",HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.GET,value="/{custId}")
    public Customer getCustomer(@PathVariable int  custId){
      return  custService.getCustomer(custId);
    }



}
