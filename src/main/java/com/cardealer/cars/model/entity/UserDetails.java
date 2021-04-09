package com.cardealer.cars.model.entity;

import com.cardealer.cars.model.entity.enums.Gender;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_details")
public class UserDetails extends BaseEntity {

    private User user;
    private String name;
    private String middleName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private LocalDate birthdate;
    private Gender gender;
    private Boolean didUserAgree;
    private String pictureUrl;
    private boolean emailConfirmed;


    public UserDetails() {
    }

    @OneToOne
    public User getPlayer() {
        return user;
    }

    public void setPlayer(User user) {
        this.user = user;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "middle_name")
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Column(nullable = false, name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(nullable = false)
    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Column(name = "picture_url")
    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Boolean getDidUserAgree() {
        return didUserAgree;
    }

    public void setDidUserAgree(Boolean didUserAgree) {
        this.didUserAgree = didUserAgree;
    }

    public boolean isEmailConfirmed() {
        return emailConfirmed;
    }

    public void setEmailConfirmed(boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }
}
