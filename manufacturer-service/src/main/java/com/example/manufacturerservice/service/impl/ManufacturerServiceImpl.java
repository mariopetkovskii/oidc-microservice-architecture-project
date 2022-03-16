package com.example.manufacturerservice.service.impl;

import com.example.manufacturerservice.model.Manufacturer;
import com.example.manufacturerservice.repository.ManufacturerRepository;
import com.example.manufacturerservice.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return this.manufacturerRepository.findById(id);
    }

    @Override
    public List<Manufacturer> listAll() {
        return this.manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> save(String name) {
        return Optional.of(this.manufacturerRepository.save(new Manufacturer(name)));
    }

    @Override
    public void deleteById(Long id) {
        this.manufacturerRepository.deleteById(id);
    }
}
