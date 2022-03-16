package com.example.carservice.controller;


import com.example.carservice.model.Car;
import com.example.carservice.model.Manufacturer;
import com.example.carservice.service.CarService;
import lombok.Data;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CarController {
    private final CarService carService;

    @Autowired
    private KeycloakRestTemplate keycloakRestTemplate;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public String getIndexPage(){
        return "index";
    }

    @GetMapping("/cars")
    public String getCarsPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Car> cars = this.carService.listAll();
        model.addAttribute("cars", cars);
        return "cars";
    }

    @DeleteMapping("/cars/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.carService.deleteById(id);
        return "redirect:/cars";
    }

    @GetMapping("/cars/add-form")
    public String addProductPage(Model model) {
        List<Car> cars = this.carService.listAll();
        model.addAttribute("cars", cars);
        PagedModel<Manufacturer> pageManufacturers = keycloakRestTemplate.getForObject("http://localhost:9092/manufacturers",PagedModel.class);
        model.addAttribute("manufacturers",pageManufacturers);
        return "add-form";
    }

    @PostMapping("/cars/add")
    public String saveProduct(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam Integer price,
            @RequestParam String description,
            @RequestParam String manufacturer) {
        this.carService.save(name,price,description,manufacturer);
        return "redirect:/cars";
    }

    @GetMapping("/manufacturers")
    public String manufacturer(Model model){
        PagedModel<Manufacturer> pageManufacturers = keycloakRestTemplate.getForObject("http://localhost:9092/manufacturers",PagedModel.class);
        model.addAttribute("manufacturers",pageManufacturers);
        return "manufacturers";
    }

    @GetMapping("/jwt")
    @ResponseBody
    public Map<String ,String> map(HttpServletRequest request){
        KeycloakAuthenticationToken token=(KeycloakAuthenticationToken) request.getUserPrincipal();
        KeycloakPrincipal principal=(KeycloakPrincipal)token.getPrincipal();
        KeycloakSecurityContext keycloakSecurityContext = principal.getKeycloakSecurityContext();
        Map<String,String> map =new HashMap<>();
        map.put("access_token",keycloakSecurityContext.getTokenString());
        return map;
    }

}
