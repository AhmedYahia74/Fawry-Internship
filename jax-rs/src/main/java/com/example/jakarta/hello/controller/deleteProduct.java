package com.example.jakarta.hello.controller;

import com.example.jakarta.hello.product.Product;
import com.example.jakarta.hello.product.ProductRepository;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("deleteProduct")
public class deleteProduct {
    @DELETE
    @Produces({ MediaType.APPLICATION_JSON })
    public boolean deleteProduct(Product product) {
        if (ProductRepository.deleteProduct(product)) {
          return true;
        }
        return false;
    }
}
