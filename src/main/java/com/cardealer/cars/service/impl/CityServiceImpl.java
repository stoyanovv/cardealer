package com.cardealer.cars.service.impl;

import com.cardealer.cars.model.entity.City;
import com.cardealer.cars.repository.CityRepository;
import com.cardealer.cars.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> getAllCities() {
        return this.cityRepository.getAllBy();
    }

    @Override
    public City findCityByName(String city) {
        return cityRepository.findByName(city);
    }

    @Override
    public void seedCities() {
        if (cityRepository.count() == 0) {
            cityRepository.save(new City("Shumen"));
            cityRepository.save(new City("Sofia"));
            cityRepository.save(new City("Varna"));
            cityRepository.save(new City("Bugras"));
        }
    }
}
