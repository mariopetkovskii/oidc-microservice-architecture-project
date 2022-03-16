package com.example.manufacturerservice.service;

import com.example.manufacturerservice.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {
    Optional<Manufacturer> findById(Long id);
    List<Manufacturer> listAll();
    Optional<Manufacturer> save(String name);
    void deleteById(Long id);

}
