package com.cardealer.cars.service;

import com.cardealer.cars.common.OutputJson;
import com.cardealer.cars.model.binding.CarBindingModel;
import com.cardealer.cars.model.view.CarInfoView;
import com.cardealer.cars.model.view.CarView;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface CarService {
    void seedCars();

    List<CarView> getAllCarsAvailable();

    CarInfoView getInfoForCar(Long carId);

    OutputJson getBoughtCars(Long id);

    OutputJson buyCar(Long id, Long carId);

    OutputJson deleteCar(Long id, Long carId);

    List<CarView> searchForMake(String make);

    OutputJson addCar(CarBindingModel carBindingModel);

    void changePrice();

}
