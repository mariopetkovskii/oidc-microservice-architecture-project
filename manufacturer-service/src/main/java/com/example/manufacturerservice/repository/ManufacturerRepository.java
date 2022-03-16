package com.example.manufacturerservice.repository;

import com.example.manufacturerservice.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
}
