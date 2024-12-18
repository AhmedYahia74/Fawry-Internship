package com.example.jakarta.hello.controller;

import com.example.jakarta.hello.product.Product;
import com.example.jakarta.hello.product.ProductRepository;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("updateProduct")
public class updateProduct {
    @POST
    @Produces({ MediaType.APPLICATION_JSON })
    public boolean update(String name,Product product) {
        if (ProductRepository.validateProduct(product)) {
            ProductRepository.updateProduct(name, product);
            return true;
        }
        return false;
    }
}
