package com.example.Exercise8;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "customers")
public class Customer {
    @Id
    private Long id;
    private String accountNumber;
    private String name;
    private String department;
    public Customer(){

    }
}
