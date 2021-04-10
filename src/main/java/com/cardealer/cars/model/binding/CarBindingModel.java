package com.cardealer.cars.model.binding;

import com.google.gson.annotations.Expose;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CarBindingModel {

    @Expose
    private String make;
    @Expose
    private String model;
    @Expose
    private LocalDate year;
    @Expose
    private String fuel;
    @Expose
    private BigDecimal price;
    @Expose
    private int enginePower;
    @Expose
    private String imgUrl;

    public CarBindingModel() {
    }

    @NotNull(message = "Трябва да се въведе марка")
    @Size(min = 2, max = 20, message = "Марката трябва да е между 2 и 20 символа")
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @NotNull(message = "Трябва да се въведе модел")
    @Size(min = 2, max = 20, message = "Името трябва да е между 2 и 20 символа")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @NotNull(message = "Трябва да се избере дата на производство")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Невалидна дата")
    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    @NotNull(message = "Трябва да се въведе вид гориво")
    @Size(min = 2, max = 20, message = "Името на горивото трябва да е между 2 и 20 символа")
    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    @NotNull(message = "Трябва да се въведе цена")
    @Min(value = 0, message = "Невалидна цена")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @NotNull(message = "Трябва да се въведе обем на мотора")
    @Min(value = 400, message = "Обемът на моторо трябва да е между 400 и 10000 куб. см.")
    @Max(value = 10000, message = "Обемът на моторо трябва да е между 400 и 10000 куб. см.")
    public int getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(int enginePower) {
        this.enginePower = enginePower;
    }

    @Size(min = 3, max = 150, message = "Пътят към снимката трябва да е между 3 и 150 символа")
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
