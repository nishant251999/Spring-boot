package com.nishant.demo1.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Address {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long address_id;
    private String addLine1;
    private String addLine2;
    private String city;
    private String country;
    private String pincode;
}
