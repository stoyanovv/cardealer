package com.cardealer.cars.repository;


import com.cardealer.cars.model.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    List<City> getAllBy();

    City findByName(String city);

}
