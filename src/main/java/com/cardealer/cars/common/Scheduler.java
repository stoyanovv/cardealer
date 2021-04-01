package com.cardealer.cars.common;

import com.cardealer.cars.model.entity.City;
import com.cardealer.cars.repository.CityRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class Scheduler {

    private final CityRepository cityRepository;

    public Scheduler(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Scheduled(fixedRate = 3600000)
    public void checkMatchesForWinner() {
//        if (cityRepository.count() == 0) {
//            City city = new City();
//            City city2 = new City();
//            City city3 = new City();
//            city.setName("Shumen");
//            city2.setName("Sofia");
//            city3.setName("Varna");
//
//            cityRepository.save(city);
//            cityRepository.save(city2);
//            cityRepository.save(city3);
//            System.out.println("Cities saved");
//        }
    }
}
