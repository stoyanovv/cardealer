package com.cardealer.cars.service.impl;

import com.cardealer.cars.common.OutputJson;
import com.cardealer.cars.model.entity.*;
import com.cardealer.cars.model.entity.enums.Role;
import com.cardealer.cars.model.service.UserLoginServiceModel;
import com.cardealer.cars.model.service.UserRegisterServiceModel;
import com.cardealer.cars.model.view.profile.MyProfileView;
import com.cardealer.cars.repository.UserRepository;
import com.cardealer.cars.repository.UserRoleRepository;
import com.cardealer.cars.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final CityService cityService;
    private final UserRoleRepository userRoleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserDetailsService userDetailsService, CityService cityService, UserRoleRepository userRoleRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
        this.cityService = cityService;
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(UserRegisterServiceModel userRegisterServiceModel) {
        User user = new User();
        UserDetails userDetails = modelMapper.map(userRegisterServiceModel, UserDetails.class);
        List<UserRole> userRoles = new ArrayList<>();
        if(userRepository.count() == 0) {
            UserRole adminRole = new UserRole();
            adminRole.setRole(Role.ADMIN);
            userRoleRepository.save(adminRole);
            UserRole userRole = new UserRole();
            userRole.setRole(Role.USER);
            userRoleRepository.save(userRole);
            userRoles.add(adminRole);
            userRoles.add(userRole);
            user.setRoles(userRoles);
        }else {
            UserRole userRole = new UserRole();
            userRole.setRole(Role.USER);
            userRoleRepository.save(userRole);
            userRoles.add(userRole);
            user.setRoles(userRoles);
        }
        user.setCity(cityService.findCityByName(userRegisterServiceModel.getCity()));
        userRepository.save(user);
        userDetails.setPlayer(user);
        userDetailsService.save(userDetails);
    }

    @Override
    public UserLoginServiceModel findByEmailAndPassword(String email, String password) {
        UserDetails userDetails = userDetailsService.findByEmail(email).orElse(null);
        if (userDetails == null){
        return null;
        }
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
         return modelMapper.map(userDetailsService.findByEmail(email).orElse(null), UserLoginServiceModel.class) ;
        }
        return null;
    }

    @Override
    public MyProfileView getMyProfile(Long playerDetailsId) {
        UserDetails userDetails = userDetailsService.getById(playerDetailsId);
        MyProfileView myProfileView = modelMapper.map(userDetails, MyProfileView.class);
        myProfileView.setAge(Period.between(userDetails.getBirthdate(), LocalDate.now()).getYears());
        return myProfileView;

    }


    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }


}

