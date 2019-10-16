package com.wipro.service;



import com.wipro.util.ShippingDetails;

import java.util.Collection;

public interface ShippingService {

    public boolean createShippingRecord(ShippingDetails shippingDetails);
    public ShippingDetails getShippingDetails(int productId);
    public Collection<ShippingDetails> getAllShippingDetails();



}

