package com.cardealer.cars.service;

import com.cardealer.cars.common.OutputJson;
import com.cardealer.cars.model.entity.User;
import com.cardealer.cars.model.service.UserLoginServiceModel;
import com.cardealer.cars.model.service.UserRegisterServiceModel;
import com.cardealer.cars.model.view.profile.MyProfileView;

import java.util.Optional;

public interface UserService {

    void register(UserRegisterServiceModel userRegisterServiceModel);

    UserLoginServiceModel findByEmailAndPassword(String email, String password);

    MyProfileView getMyProfile(Long id);


    Optional<User> findById(Long id);

    void save(User user);
}
