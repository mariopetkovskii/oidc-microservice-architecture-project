package com.example.carservice.service;

import com.example.carservice.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    Optional<Car> findById(Long id);
    List<Car> listAll();

    Optional<Car> save(String name, Integer price, String description,String manufacturer);

    void deleteById(Long id);
}
