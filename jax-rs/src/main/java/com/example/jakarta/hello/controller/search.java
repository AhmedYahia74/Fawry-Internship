package com.example.jakarta.hello.controller;

import com.example.jakarta.hello.product.Product;
import com.example.jakarta.hello.product.ProductRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.Optional;

@Path("search")
public class search {
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Product findProduct(@QueryParam("name") String name) {
        Optional<Product> product=ProductRepository.findProduct(name);
        return product.orElse(null);
    }
}
