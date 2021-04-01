package com.cardealer.cars.common;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.time.LocalDate;


public class InputJson {

    @Expose
    private Long carId;
    @Expose
    private String make;

    public InputJson() {
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }
}
