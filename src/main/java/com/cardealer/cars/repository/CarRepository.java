package com.cardealer.cars.repository;

import com.cardealer.cars.model.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> getAllByBought(boolean bought);

    Car getById(Long id);

    List<Car> getAllByUserId(Long id);

    List<Car> getAllByMakeAndBought(String make, boolean bought);
}
