package com.cardealer.cars.service.impl;

import com.cardealer.cars.model.entity.*;
import com.cardealer.cars.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class CityServiceImplTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CityRepository cityRepository;

    private long TEST_CITY1_ID, TEST_CITY2_ID;
    private String TEST_CITY1_NAME = "Sofia", TEST_CITY2_NAME = "Shumen";

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
    }

    @Test
    public void testCityCount() throws Exception {
    }
}
