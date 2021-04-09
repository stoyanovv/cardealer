package com.cardealer.cars.service;

import com.cardealer.cars.model.entity.UserDetails;
import com.cardealer.cars.model.service.UserLoginServiceModel;
import com.cardealer.cars.model.service.UserRegisterServiceModel;

import java.util.Optional;

public interface UserDetailsService {

    UserDetails getById(Long id);

    UserRegisterServiceModel findPlayerByEmail(String email);

    UserLoginServiceModel findByEmailAndPassword(String email, String password);

    void save(UserDetails userDetails);

    UserDetails findByEmailSetToken(String email);

    UserDetails getByPlayerId(Long id);


    Optional<UserDetails> findByEmail(String email);

    UserDetails getByEmail(String email);

    void confirmEmail(String email);

    boolean checkEmailConfirmed(String email);

    void delete(UserDetails userDetails);
}
