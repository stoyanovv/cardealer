package com.cardealer.cars.web.controller;

import com.cardealer.cars.common.OutputJson;
import com.cardealer.cars.model.binding.CarBindingModel;
import com.cardealer.cars.model.binding.UserRegisterBindingModel;
import com.cardealer.cars.model.service.UserRegisterServiceModel;
import com.cardealer.cars.service.CarService;
import com.cardealer.cars.util.ValidationUtil;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@RestController
@RequestMapping("/admin")
public class  AdminController {

    private final Gson gson;
    private final CarService carService;
    private final ValidationUtil validationUtil;

    public AdminController(Gson gson, CarService carService, ValidationUtil validationUtil) {
        this.gson = gson;
        this.carService = carService;
        this.validationUtil = validationUtil;
    }

    @CrossOrigin
    @PostMapping("/addcar")
    public OutputJson deleteCar(@RequestBody String carInfo) {
        OutputJson outputJson = new OutputJson();
        CarBindingModel carBindingModel = gson.fromJson(carInfo, CarBindingModel.class);
        if (validationUtil.isValid(carBindingModel)) {
            return carService.addCar(carBindingModel);
        }
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<CarBindingModel>> violations = validator.validate(carBindingModel);
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation<CarBindingModel> violation : violations) {
            sb.append(violation.getMessage())
                    .append(System.lineSeparator());
        }
        outputJson.setMessage(sb.toString());
        outputJson.setSuccess(false);
        return outputJson;
    }

}
