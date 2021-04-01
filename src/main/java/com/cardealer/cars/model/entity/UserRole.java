package com.cardealer.cars.model.entity;

import com.cardealer.cars.model.entity.enums.Role;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity {

    private Role role;

    @Enumerated(EnumType.STRING)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
