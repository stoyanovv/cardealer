package com.cardealer.cars.web.controller;

import com.cardealer.cars.common.OutputJson;
import com.cardealer.cars.model.binding.UserLoginBindingModel;
import com.cardealer.cars.model.binding.UserRegisterBindingModel;
import com.cardealer.cars.model.service.UserLoginServiceModel;
import com.cardealer.cars.model.service.UserRegisterServiceModel;
import com.cardealer.cars.model.view.profile.MyProfileView;
import com.cardealer.cars.service.UserDetailsService;
import com.cardealer.cars.service.UserService;
import com.cardealer.cars.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.Set;

@RestController
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final OutputJson outputJson;
    private final UserDetailsService userDetailsService;
    private final ValidationUtil validationUtil;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService,
                          ModelMapper modelMapper,
                          Gson gson,
                          OutputJson outputJson,
                          UserDetailsService userDetailsService,
                          ValidationUtil validationUtil, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.outputJson = outputJson;
        this.userDetailsService = userDetailsService;
        this.validationUtil = validationUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @CrossOrigin
    @PostMapping("/register")
    public OutputJson  register(@RequestBody String registerForm) {
        UserRegisterBindingModel userRegisterBindingModel = gson.fromJson(registerForm, UserRegisterBindingModel.class);
        if (validationUtil.isValid(userRegisterBindingModel)) {
            if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
                outputJson.setSuccess(false);
                outputJson.setMessage("Паролите не съвпадат");
                return outputJson;
            }
//            else if (Period.between(userRegisterBindingModel.getBirthdate(), LocalDate.now()).getYears() < 18) {
//                outputJson.setSuccess(false);
//                outputJson.setMessage("Минималната възраст за регистрация е 18 години");
//                return outputJson;
//            }
            else if(userDetailsService.findPlayerByEmail(userRegisterBindingModel.getEmail()) != null) {
                outputJson.setSuccess(false);
                outputJson.setMessage("Вече е регистриран играч с този имейл адрес");
                return outputJson;
            }
            userRegisterBindingModel.setEmail(userRegisterBindingModel.getEmail());
            userRegisterBindingModel.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));
            userService.register(modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class));
            outputJson.setSuccess(true);
            outputJson.setMessage("На посочения от вас имейл беше изпратен линк за потвърждение. Моля влезте в пощата си и кликнете на линка.");
            return outputJson;
        }
        StringBuilder sb = new StringBuilder();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<UserRegisterBindingModel>> violations = validator.validate(userRegisterBindingModel);
        for (ConstraintViolation<UserRegisterBindingModel> violation : violations) {
            sb.append(violation.getMessage())
                    .append(System.lineSeparator());
        }
        outputJson.setSuccess(false);
        outputJson.setMessage(sb.toString());
        return outputJson;
    }

    @CrossOrigin
    @PostMapping("/signin")
    public OutputJson loginConfirm(@RequestBody String loginForm) {
        UserLoginBindingModel userLoginBindingModel = gson.fromJson(loginForm, UserLoginBindingModel.class);
        if (validationUtil.isValid(userLoginBindingModel)) {
        UserLoginServiceModel userLoginServiceModel = userService
        .findByEmailAndPassword(userLoginBindingModel.getEmail(),
                       userLoginBindingModel.getPassword());
        if (userLoginServiceModel == null) {
            outputJson.setSuccess(false);
            outputJson.setMessage("Невалиден имейл адрес или парола");
            return outputJson;
        }
        if (!userDetailsService.checkEmailConfirmed(userLoginBindingModel.getEmail())) {
            outputJson.setSuccess(false);
            outputJson.setMessage("Трябва да потвърдите имейла си");
            return outputJson;
        }
        outputJson.setSuccess(true);
        outputJson.setMessage("Успешно се логнахте");
        outputJson.setUserId(userLoginServiceModel.getId());
        outputJson.setName(userLoginServiceModel.getName());
        return outputJson;
        }
        StringBuilder sb = new StringBuilder();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<UserLoginBindingModel>> violations = validator.validate(userLoginBindingModel);
        for (ConstraintViolation<UserLoginBindingModel> violation : violations) {
            sb.append(violation.getMessage())
                    .append(System.lineSeparator());
        }
        outputJson.setSuccess(false);
        outputJson.setMessage(sb.toString());
        return outputJson;
    }

    @CrossOrigin
    @GetMapping("/myprofile/{id}")
    public OutputJson myProfile(@PathVariable Long id) {
        OutputJson outputJson = new OutputJson();
        MyProfileView myProfileByPlayerDetailsId = userService.getMyProfile(id);
        if (myProfileByPlayerDetailsId == null) {
            outputJson.setSuccess(false);
            outputJson.setMessage("Няма данни");
            return outputJson;
        }
        outputJson.setSuccess(true);
        outputJson.setMyProfileView(myProfileByPlayerDetailsId);
        return outputJson;
    }

    @CrossOrigin
    @PostMapping("/login")
    public void tokenLogin() {
    }

    @CrossOrigin
    @GetMapping("/confirm")
    public OutputJson confirm(@RequestParam("token") String token) {
        return userService.confirmToken(token);
    }

}
