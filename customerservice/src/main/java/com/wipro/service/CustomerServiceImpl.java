package com.wipro.service;

import com.wipro.util.Customer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

    Map<Integer, Customer> custMap=new HashMap<>();

    public boolean createCustomer(Customer cust){
        if(custMap.containsKey(cust.getCustomerID())){
            return false;
        }
        custMap.put(cust.getCustomerID(),cust);
        return true;
    }


    public Customer getCustomer(int id) {
        return custMap.get(id);
    }
}
