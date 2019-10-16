package com.wipro.service;

import com.wipro.util.Customer;

public interface CustomerService {

    public boolean createCustomer(Customer cust);
    public Customer getCustomer(int id);



}

