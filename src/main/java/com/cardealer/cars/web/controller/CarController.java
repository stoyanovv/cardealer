package com.cardealer.cars.web.controller;

import com.cardealer.cars.common.InputJson;
import com.cardealer.cars.common.OutputJson;
import com.cardealer.cars.model.view.CarInfoView;
import com.cardealer.cars.model.view.CarView;
import com.cardealer.cars.service.CarService;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {

    private final CarService carService;
    private final Gson gson;

    public CarController(CarService carService, Gson gson) {
        this.carService = carService;
        this.gson = gson;
    }

    @CrossOrigin
    @GetMapping("/carinfo/{carId}")
    public CarInfoView getInfoForCar(@PathVariable Long carId) {
        return carService.getInfoForCar(carId);
    }

    @CrossOrigin
    @GetMapping("/boughtcars/{id}")
    public OutputJson getBoughtCars(@PathVariable Long id) {
        return carService.getBoughtCars(id);
    }

    @CrossOrigin
    @PostMapping("/buycar/{id}")
    public OutputJson buyCar(@PathVariable Long id, @RequestBody String carId) {
        InputJson inputJson = gson.fromJson(carId, InputJson.class);
        return carService.buyCar(id, inputJson.getCarId());
    }

    @CrossOrigin
    @PostMapping("/deletecar/{id}")
    public OutputJson deleteCar(@PathVariable Long id, @RequestBody String carId) {
        InputJson inputJson = gson.fromJson(carId, InputJson.class);
        return carService.deleteCar(id, inputJson.getCarId());
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
}
