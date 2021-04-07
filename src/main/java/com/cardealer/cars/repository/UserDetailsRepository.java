package com.cardealer.cars.repository;

import com.cardealer.cars.model.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

    UserDetails getById(Long id);

    UserDetails getByPlayerId(Long playerId);

    Optional<UserDetails> findByEmail(String email);

    Optional<UserDetails> findByEmailAndPassword(String email, String password);

    UserDetails getByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE UserDetails ud " +
            "SET ud.emailConfirmed = TRUE WHERE ud.email = :email")
    void confirmEmail(String email);
}
