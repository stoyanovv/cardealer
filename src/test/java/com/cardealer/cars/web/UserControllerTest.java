package com.cardealer.cars.web;

import com.cardealer.cars.model.entity.*;
import com.cardealer.cars.model.entity.enums.Gender;
import com.cardealer.cars.model.entity.enums.Role;
import com.cardealer.cars.repository.CityRepository;
import com.cardealer.cars.repository.UserDetailsRepository;
import com.cardealer.cars.repository.UserRepository;
import com.cardealer.cars.repository.UserRoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDetailsRepository userDetailsRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private CityRepository cityRepository;


    private long TEST_USER1_ID, TEST_USER2_ID;
    private String TEST_USER1_NAME = "Pesho", TEST_USER2_NAME = "Vanq";
    private String TEST_USER1_LAST_NAME = "Peshov", TEST_USER2_LAST_NAME = "Vaneva";
    private String TEST_USER1_EMAIL = "pesho@abv.bg", TEST_USER2_EMAIL = "vanq@abv.bg";
    private String TEST_USER1_PASSWORD = "asdasd1", TEST_USER2_PASSWORD = "asdasd1";
    private String TEST_USER1_PHONE_NUMBER = "22222222222", TEST_USER2_PHONE_NUMBER = "111111111111111";
    private LocalDate TEST_USER1_BIRTHDATE = LocalDate.now().minusYears(20), TEST_USER2_BIRTHDATE = LocalDate.now();
    private Gender TEST_USER1_GENDER = Gender.MALE, TEST_USER2_GENDER = Gender.FEMALE;
    private User user1 = new User();
    private UserDetails userDetails1 = new UserDetails();
    private Set<UserRole> userRoles1 = new HashSet<>();
    private UserRole userRole1 = new UserRole();
    private City city1 = new City();
    private User user2 = new User();
    private UserDetails userDetails2 = new UserDetails();
    private Set<UserRole> userRoles2 =new HashSet<>();
    private UserRole userRole2 = new UserRole();

    @BeforeEach
    public void setUp() {

        city1.setName("Sofia");
        cityRepository.save(city1);
        userRole1.setRole(Role.ADMIN);
        userRoles1.add(userRole1);
        user1.setCity(city1);
        user1.setRoles(userRoles1);
        userDetails1.setName(TEST_USER1_NAME);
        userDetails1.setLastName(TEST_USER1_LAST_NAME);
        userDetails1.setEmail(TEST_USER1_EMAIL);
        userDetails1.setPassword(TEST_USER1_PASSWORD);
        userDetails1.setBirthdate(TEST_USER1_BIRTHDATE);
        userDetails1.setGender(TEST_USER1_GENDER);
        userDetails1.setPhoneNumber(TEST_USER1_PHONE_NUMBER);
        userDetails1.setPlayer(user1);
        userRoleRepository.save(userRole1);
        cityRepository.save(city1);
        userRepository.save(user1);
        userDetailsRepository.save(userDetails1);
        TEST_USER1_ID = userDetails1.getId();

        userRole2.setRole(Role.USER);
        userRoles2.add(userRole2);
        user2.setCity(city1);
        user2.setRoles(userRoles2);
        userDetails2.setName(TEST_USER2_NAME);
        userDetails2.setLastName(TEST_USER2_LAST_NAME);
        userDetails2.setEmail(TEST_USER2_EMAIL);
        userDetails2.setPassword(TEST_USER2_PASSWORD);
        userDetails2.setBirthdate(TEST_USER2_BIRTHDATE);
        userDetails2.setGender(TEST_USER2_GENDER);
        userDetails2.setPhoneNumber(TEST_USER2_PHONE_NUMBER);
        userDetails2.setPlayer(user2);
        userRoleRepository.save(userRole2);
        userRepository.save(user2);
        userDetailsRepository.save(userDetails2);
        TEST_USER2_ID = userDetails2.getId();
    }


    @AfterEach
    public void tearDown() {
        userDetailsRepository.delete(userDetails2);
        userDetailsRepository.delete(userDetails1);
        userRepository.delete(user1);
        userRepository.delete(user2);
        userRoleRepository.delete(userRole1);
        userRoleRepository.delete(userRole2);
        cityRepository.delete(city1);
    }

    @Test
    public void testMyProfile() throws Exception {
        this.mockMvc.perform(get("/myprofile/{id}", TEST_USER1_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.myProfileView.name", is(TEST_USER1_NAME)))
                .andExpect(jsonPath("$.myProfileView.email", is(TEST_USER1_EMAIL)))
                .andExpect(jsonPath("$.myProfileView.lastName", is(TEST_USER1_LAST_NAME)))
                .andExpect(jsonPath("$.myProfileView.phoneNumber", is(TEST_USER1_PHONE_NUMBER)));
    }

}
