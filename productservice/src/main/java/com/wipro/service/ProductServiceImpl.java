package com.wipro.service;

import com.wipro.util.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    Map<Integer, Product> productMap=new HashMap();


    public boolean createProduct(Product product) {
        if(productMap.containsKey(product.getProductID())){
           return false;
        }
        productMap.put(product.getProductID(),product);
        return true;
    }

    public Product getProduct(int productId) {
        if(productMap.containsKey(productId)){
            return productMap.get(productId);
        }
        return null;
    }

    public Collection<Product> getAllProducts() {
        Collection<Product> productCollection=new ArrayList<Product>();
        Iterator iterator=productMap.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Integer,Product> entry= (Map.Entry<Integer, Product>) iterator.next();
            productCollection.add(entry.getValue());
        }
        return productCollection;
    }
}
