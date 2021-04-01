package com.cardealer.cars.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cities")
public class City extends BaseEntity{

    private String name;


    public City() {

    }

    public City(String name) {
        this.name = name;
    }


    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
