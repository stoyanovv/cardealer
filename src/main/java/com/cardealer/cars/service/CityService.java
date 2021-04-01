package com.cardealer.cars.service;

import com.cardealer.cars.model.entity.City;

import java.util.List;

public interface CityService {

    List<City> getAllCities();

    City findCityByName(String city);

    void seedCities();
}
