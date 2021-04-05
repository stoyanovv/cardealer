package com.cardealer.cars.web.controller;

import com.cardealer.cars.common.InputJson;
import com.cardealer.cars.common.OutputJson;
import com.cardealer.cars.model.view.CarView;
import com.cardealer.cars.service.CarService;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShopController {

    private final CarService carService;
    private final Gson gson;

    public ShopController(CarService carService, Gson gson) {
        this.carService = carService;
        this.gson = gson;
    }

    @CrossOrigin
    @GetMapping("/shop")
    public List<CarView> getAllCarsAvailable() {
        return carService.getAllCarsAvailable();
    }

    @CrossOrigin
    @PostMapping("/search")
    public List<CarView> searchForMake(@RequestBody String make) {
        InputJson inputJson = gson.fromJson(make, InputJson.class);
        return carService.searchForMake(inputJson.getMake());
    }

    @CrossOrigin
    @PostMapping("/buycar/{id}")
    public OutputJson buyCar(@PathVariable Long id, @RequestBody String carId) {
        InputJson inputJson = gson.fromJson(carId, InputJson.class);
        return carService.buyCar(id, inputJson.getCarId());
    }
}
