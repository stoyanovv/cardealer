package com.cardealer.cars.service.impl;

import com.cardealer.cars.model.entity.UserDetails;
import com.cardealer.cars.model.service.UserLoginServiceModel;
import com.cardealer.cars.model.service.UserRegisterServiceModel;
import com.cardealer.cars.repository.UserDetailsRepository;
import com.cardealer.cars.service.UserDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;
    private final ModelMapper modelMapper;

    public UserDetailsServiceImpl(UserDetailsRepository userDetailsRepository, ModelMapper modelMapper) {
        this.userDetailsRepository = userDetailsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetails getById(Long id) {
        return this.userDetailsRepository.getById(id);
    }

    @Override
    public UserRegisterServiceModel findPlayerByEmail(String email) {
        return userDetailsRepository.findByEmail(email)
                .map(playerDetails -> modelMapper.map(playerDetails, UserRegisterServiceModel.class))
                .orElse(null);
    }


    @Override
    public UserLoginServiceModel findByEmailAndPassword(String email, String password) {
        return userDetailsRepository.findByEmailAndPassword(email, password).map(user -> modelMapper.map(user, UserLoginServiceModel.class))
                .orElse(null);
    }

    @Override
    public void save(UserDetails userDetails) {
        userDetailsRepository.save(userDetails);
    }

    @Override
    public UserDetails findByEmailSetToken(String email) {
        return userDetailsRepository.findByEmail(email)
                .orElse(null);
    }

    @Override
    public UserDetails getByPlayerId(Long playerId) {
        return userDetailsRepository.getByPlayerId(playerId);
    }


    @Override
    public Optional<UserDetails> findByEmail(String email) {
        return userDetailsRepository.findByEmail(email);
    }

    @Override
    public UserDetails getByEmail(String email) {
        return userDetailsRepository.getByEmail(email);
    }

    @Override
    public void confirmEmail(String email) {
        userDetailsRepository.confirmEmail(email);
    }

    @Override
    public boolean checkEmailConfirmed(String email) {
        return userDetailsRepository.findByEmail(email).orElse(null).isEmailConfirmed();
    }

    @Override
    public void delete(UserDetails userDetails) {
        userDetailsRepository.delete(userDetails);
    }

}
