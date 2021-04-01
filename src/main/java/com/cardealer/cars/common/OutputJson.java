package com.cardealer.cars.common;

import com.cardealer.cars.model.view.CarView;
import com.cardealer.cars.model.view.profile.MyProfileView;

import java.math.BigDecimal;
import java.util.List;

public class OutputJson {

    private boolean success;
    private String message;
    private String userId;
    private String name;
    private String phoneNumber;
    private MyProfileView myProfileView;
    private List<CarView> cars;
    private BigDecimal price;

    public OutputJson() {

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public MyProfileView getMyProfileView() {
        return myProfileView;
    }

    public void setMyProfileView(MyProfileView myProfileView) {
        this.myProfileView = myProfileView;
    }

    public List<CarView> getCars() {
        return cars;
    }

    public void setCars(List<CarView> cars) {
        this.cars = cars;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
