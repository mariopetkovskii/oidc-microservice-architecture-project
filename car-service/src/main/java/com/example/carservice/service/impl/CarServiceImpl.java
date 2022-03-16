package com.example.carservice.service.impl;

import com.example.carservice.model.Car;
import com.example.carservice.repository.CarRepository;
import com.example.carservice.service.CarService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Optional<Car> findById(Long id) {
        return this.carRepository.findById(id);
    }

    @Override
    public List<Car> listAll() {
        return this.carRepository.findAll();
    }

    @Override
    public Optional<Car> save(String name, Integer price, String description, String manufacturer) {
        return Optional.of(this.carRepository.save(new Car(name,price,description, manufacturer)));
    }

    @Override
    public void deleteById(Long id) {
        this.carRepository.deleteById(id);
    }
}
