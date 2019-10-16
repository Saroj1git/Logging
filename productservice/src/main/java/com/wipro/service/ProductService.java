package com.wipro.service;


import com.wipro.util.Product;

import java.util.Collection;
import java.util.List;

public interface ProductService {

    public boolean createProduct(Product cust);
    public Product getProduct(int id);
    public Collection<Product> getAllProducts();



}

