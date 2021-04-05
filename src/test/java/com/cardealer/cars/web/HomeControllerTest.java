package com.cardealer.cars.web;

import com.cardealer.cars.model.entity.Car;
import com.cardealer.cars.model.entity.City;
import com.cardealer.cars.repository.CarRepository;
import com.cardealer.cars.repository.CityRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CityRepository cityRepository;

    private long TEST_CITY1_ID, TEST_CITY2_ID;
    private String TEST_CITY1_NAME = "Sofia", TEST_CITY2_NAME = "Shumen";

    private long TEST_CAR1_ID, TEST_CAR2_ID;
    private String TEST_CAR1_MAKE = "Audi", TEST_CAR2_MAKE = "VW";
    private String TEST_CAR1_MODEL = "A6", TEST_CAR2_MODEL = "Golf";
    private LocalDate TEST_CAR1_YEAR = LocalDate.now(), TEST_CAR2_YEAR = LocalDate.now().minusYears(30);
    private String TEST_CAR1_FUEL = "Diesel", TEST_CAR2_FUEL = "Gasoline";
    private BigDecimal TEST_CAR1_PRICE = BigDecimal.valueOf(55600), TEST_CAR2_PRICE = BigDecimal.valueOf(10222);
    private int TEST_CAR1_ENGINE_POWER = 3000, TEST_CAR2_ENGINE_POWER = 1900;
    private String TEST_CAR1_IMG_URL = "nqma", TEST_CAR2_IMG_URL = "ima";

    @BeforeEach
    public void setUp() {
        cityRepository.deleteAll();

        City city1 = new City();
        city1.setName(TEST_CITY1_NAME);
        cityRepository.save(city1);
        TEST_CITY1_ID = city1.getId();

        City city2 = new City();
        city2.setName(TEST_CITY2_NAME);
        cityRepository.save(city2);
        TEST_CITY2_ID = city2.getId();
        carRepository.deleteAll();

        Car car1 = new Car();
        car1.setMake(TEST_CAR1_MAKE);
        car1.setModel(TEST_CAR1_MODEL);
        car1.setYear(TEST_CAR1_YEAR);
        car1.setFuel(TEST_CAR1_FUEL);
        car1.setPrice(TEST_CAR1_PRICE);
        car1.setEnginePower(TEST_CAR1_ENGINE_POWER);
        car1.setImgUrl(TEST_CAR1_IMG_URL);
        carRepository.save(car1);
        TEST_CAR1_ID = car1.getId();

        Car car2 = new Car();
        car2.setMake(TEST_CAR2_MAKE);
        car2.setModel(TEST_CAR2_MODEL);
        car2.setYear(TEST_CAR2_YEAR);
        car2.setFuel(TEST_CAR2_FUEL);
        car2.setPrice(TEST_CAR2_PRICE);
        car2.setEnginePower(TEST_CAR2_ENGINE_POWER);
        car2.setImgUrl(TEST_CAR2_IMG_URL);
        carRepository.save(car2);
        TEST_CAR2_ID = car2.getId();
    }

    @AfterEach
    public void tearDown() {
        carRepository.deleteAll();
    }

    @Test
    public void testCarsReturnCorrectStatusCode() throws Exception {
        this.mockMvc.perform(get("/cars"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCarFound() throws Exception {
        this.mockMvc.perform(get("/carinfo/{id}", TEST_CAR1_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.make", is(TEST_CAR1_MAKE)))
                .andExpect(jsonPath("$.model", is(TEST_CAR1_MODEL)))
//                .andExpect(jsonPath("$.year", is(TEST_CAR1_YEAR)))
                .andExpect(jsonPath("$.fuel", is(TEST_CAR1_FUEL)))
//                .andExpect(jsonPath("$.price", is(TEST_CAR1_PRICE)))
                .andExpect(jsonPath("$.enginePower", is(TEST_CAR1_ENGINE_POWER)))
                .andExpect(jsonPath("$.imgUrl", is(TEST_CAR1_IMG_URL)));
    }




}
