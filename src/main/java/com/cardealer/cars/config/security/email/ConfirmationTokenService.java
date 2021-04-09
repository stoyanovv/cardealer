package com.cardealer.cars.config.security.email;

import com.cardealer.cars.model.entity.ConfirmationToken;
import com.cardealer.cars.repository.ConfirmationTokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public ConfirmationTokenService(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public void setConfirmedAt(String token) {
         confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }


    public void delete(ConfirmationToken token) {
        confirmationTokenRepository.delete(token);
    }
}
