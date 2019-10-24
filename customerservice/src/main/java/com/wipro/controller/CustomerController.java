package com.wipro.controller;


import com.wipro.advice.TrackExecutionTime;
import com.wipro.service.CustomerService;
import com.wipro.util.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collection;

@RestController
@RequestMapping("/customer")
@EnableSwagger2
public class CustomerController {

    @Autowired
    private CustomerService custService;

    @RequestMapping(method = RequestMethod.POST)
    @TrackExecutionTime
    public ResponseEntity createCustomer(@RequestBody Customer cust){
       boolean custRecCreated= custService.createCustomer(cust);
       if(custRecCreated){
           return new ResponseEntity<>("Customer Record Created",HttpStatus.CREATED);
       }
        return new ResponseEntity<>("Customer Record Not Created",HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.GET,value="/{custId}")
    @TrackExecutionTime
    public Customer getCustomer(@PathVariable int  custId){
      return  custService.getCustomer(custId);
    }


    @RequestMapping(method = RequestMethod.GET)
    @TrackExecutionTime
    public Collection<Customer> getProductById(){

        return  custService.getAllCustomers();
    }



}
