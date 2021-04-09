package com.cardealer.cars.service.impl;

import com.cardealer.cars.model.entity.UserRole;
import com.cardealer.cars.repository.UserRoleRepository;
import com.cardealer.cars.service.UserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void save(UserRole userRole) {
        userRoleRepository.save(userRole);
    }

    @Override
    public void delete(UserRole role) {
        userRoleRepository.delete(role);
    }
}
