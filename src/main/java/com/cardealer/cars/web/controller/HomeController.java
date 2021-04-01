package com.cardealer.cars.web.controller;

import com.cardealer.cars.service.CarService;
import com.cardealer.cars.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

    private final CityService cityService;
    private final CarService carService;


    @Autowired
    public HomeController(CityService cityService, CarService carService) {
        this.cityService = cityService;
        this.carService = carService;
    }

    @CrossOrigin
    @GetMapping
    public String Hello() {
        return "<h1>This is home page</h1>";

    }

    @CrossOrigin
    @GetMapping("/cars")
    void seedCitiesAndCars() {
        carService.seedCars();
        cityService.seedCities();
    }


}




