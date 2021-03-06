package com.example.manufacturerservice.controller;

import com.example.manufacturerservice.model.Manufacturer;
import com.example.manufacturerservice.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manufacturer")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getManufacturerPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Manufacturer> manufacturers = this.manufacturerService.listAll();
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("bodyContent", "manufacturer");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        this.manufacturerService.deleteById(id);
        return "redirect:/manufacturer";
    }

    @GetMapping("/add-form")
    public String addCarPage(Model model) {
        model.addAttribute("bodyContent", "add-form");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveCar(
            @RequestParam(required = false) Long id,
            @RequestParam String name) {
        this.manufacturerService.save(name);
        return "redirect:/manufacturer";
    }




}