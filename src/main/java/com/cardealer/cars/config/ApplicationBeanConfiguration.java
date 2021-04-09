package com.cardealer.cars.config;

import com.cardealer.cars.common.OutputJson;
import com.cardealer.cars.util.ValidationUtilImpl;
import com.google.gson.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                    @Override
                    public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                        return LocalDateTime
                                .parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")); }})
                .registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
                    @Override
                    public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                        if (json.getAsString().trim().equals("")) {
                            return null;
                        }
                        return LocalDate
                                .parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")); }})
                .create();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public OutputJson outputJson() {
        return new OutputJson();
    }


    @Bean
    public ValidationUtilImpl validatorUtil() {
        return new ValidationUtilImpl(validator());
    }

    @Bean
    public Validator validator(){
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

}
