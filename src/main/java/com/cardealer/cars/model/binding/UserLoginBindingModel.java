package com.cardealer.cars.model.binding;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.*;

public class UserLoginBindingModel {

    @Expose
    private String email;
    @Expose
    private String password;

    public UserLoginBindingModel() {
    }

    @NotBlank(message = "Трябва да се въведе имейл адрес")
    @Email(message = "Невалиден имейл адрес")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Size(min = 6, max = 25, message = "Паролата трябва да е между 6 и 25 символа")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

