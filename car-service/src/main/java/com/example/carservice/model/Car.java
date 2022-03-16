package com.example.carservice.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    private String description;

    private String manufacturer;

    public Car() {
    }

    public Car(String name, Integer price, String description, String manufacturer) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.manufacturer = manufacturer;
    }
}
