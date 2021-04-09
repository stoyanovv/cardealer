package com.cardealer.cars.model.entity;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private LocalDateTime lastLogin;
    private City city;
    private Set<UserRole> roles;
    private Set<Car> cars;

    public User() {
    }

    @Column(name = "last_login")
    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }


    @ManyToMany(fetch = FetchType.EAGER)
    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
}

