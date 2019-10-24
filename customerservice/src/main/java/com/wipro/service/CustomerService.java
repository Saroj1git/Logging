package com.wipro.service;

import com.wipro.util.Customer;

import java.util.Collection;

public interface CustomerService {

    public boolean createCustomer(Customer cust);
    public Customer getCustomer(int id);
    public Collection<Customer>getAllCustomers();



}

