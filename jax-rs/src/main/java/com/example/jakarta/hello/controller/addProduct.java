package com.example.jakarta.hello.controller;

import com.example.jakarta.hello.product.Product;
import com.example.jakarta.hello.product.ProductRepository;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("addProduct")
public class addProduct {
    @POST
    @Produces({ MediaType.APPLICATION_JSON })
    public Product addProduct(Product product) {
        if (ProductRepository.validateProduct(product)) {
            ProductRepository.addProduct(product);
            return product;
        }
        return null;
    }
}
