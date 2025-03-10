package com.example.Exercise8;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;

    public CustomerController(CustomerRepository customerRepository, CustomerService customerService) {
        this.customerRepository = customerRepository;
        this.customerService = customerService;
    }

    @GetMapping("/searchByName")
    public List<Customer> searchCustomersByName(
            @RequestParam(defaultValue = "ahmed") String name) {
        return customerRepository.findByName(name);
    }
    @GetMapping("/searchByDepartment")
    public List<Customer> searchCustomersByDepartment(
            @RequestParam(required = false) String department) {
        return customerRepository.findByDepartment(department);
    }
    @GetMapping
    public Page<Customer> getAllCustomers(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        return customerService.findAll(pageNo, pageSize, sortBy, sortDirection);
    }

}
