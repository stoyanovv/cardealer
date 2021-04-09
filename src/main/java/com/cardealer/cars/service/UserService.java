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


    User getById(Long id);

    void save(User user);

    OutputJson confirmToken(String token);
}
