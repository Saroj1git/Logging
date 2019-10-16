package com.wipro.service;


import com.wipro.util.ShippingDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShippingServiceImpl implements ShippingService {

    Map<Integer, ShippingDetails> shippingDetailsMap=new HashMap();


    public boolean createShippingRecord(ShippingDetails shippingDetails) {
        if(shippingDetailsMap.containsKey(shippingDetails.getProductId())){
           return false;
        }
        shippingDetailsMap.put(shippingDetails.getProductId(),shippingDetails);
        return true;
    }

    public ShippingDetails getShippingDetails(int productId) {
        if(shippingDetailsMap.containsKey(productId)){
            return shippingDetailsMap.get(productId);
        }
        return null;
    }

    public Collection<ShippingDetails> getAllShippingDetails() {
        Collection<ShippingDetails> shippingDetailsCollection=new ArrayList<ShippingDetails>();
        Iterator iterator=shippingDetailsMap.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Integer,ShippingDetails> entry= (Map.Entry<Integer, ShippingDetails>) iterator.next();
            shippingDetailsCollection.add(entry.getValue());
        }
        return shippingDetailsCollection;
    }
}
