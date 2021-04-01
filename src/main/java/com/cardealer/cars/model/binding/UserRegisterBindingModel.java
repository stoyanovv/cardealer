package com.cardealer.cars.model.binding;

import com.cardealer.cars.model.entity.enums.Gender;
import com.google.gson.annotations.Expose;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class UserRegisterBindingModel {

    @Expose
    private String name;
    @Expose
    private String lastName;
    @Expose
    private String email;
    @Expose
    private String password;
    @Expose
    private String confirmPassword;
    @Expose
    private String phoneNumber;
    @Expose
    private LocalDate birthdate;
    @Expose
    private Gender gender;
    @Expose
    private String city;

    public UserRegisterBindingModel() {
    }


    @NotNull(message = "Трябва да се въведе име")
    @Size(min = 2, max = 20, message = "Името трябва да е между 2 и 20 символа на кирилица")
    @Pattern(regexp = "^[A-Za-z]*$", message = "Невалидно име")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "Трябва да се въведе фамилия")
    @Size(min = 2, max = 20, message = "Фамилията трябва да е между 2 и 20 символа на кирилица")
    @Pattern(regexp = "^[A-Za-z]*$", message = "Невалидна фамилия")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotBlank(message = "Трябва да се въведе имейл адрес")
    @Email(message = "Невалиден имейл адрес")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull(message = "Трябва да се въведе парола")
    @Size(min = 6, max = 25, message = "Паролата трябва да е между 6 и 25 символа")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z]).{6,25}$", message = "Паролата трябва да садържа минимум 1 буква и минимум 1 цифра")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull(message = "Трябва да се въведе парола")
    @Size(min = 6, max = 25, message = "Паролата трябва да е между 6 и 25 символа")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @NotNull(message = "Трябва да се въведе телефонен номер")
    @Size(min = 9, max = 20, message = "Телефонният номер е невалиден")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @NotNull(message = "Трябва да се избере дата на раждане")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Невалидна дата")
    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    @NotNull(message = "Трябва да се избере пол")
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @NotNull(message = "Трябва да се избере град")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
