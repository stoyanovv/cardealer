package com.cardealer.cars.common;

import com.cardealer.cars.service.CarService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class Scheduler {

    private final CarService carService;

    public Scheduler(CarService carService) {
        this.carService = carService;
    }

    @Scheduled(fixedRate = 86400000)
    public void checkMatchesForWinner() {
        carService.changePrice();
    }
}
