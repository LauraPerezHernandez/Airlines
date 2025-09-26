package com.unimagdalena.airlines.domain.repositories;

import com.unimagdalena.airlines.entities.Passenger;
import com.unimagdalena.airlines.entities.PassengerProfile;
import com.unimagdalena.airlines.repositories.PassengerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PassengerRepositoryTest extends AbstractRepository {

    @Autowired PassengerRepository passengerRepository;

    @Test @DisplayName("Passenger: find by email (ignore case) and fetch the profile")
    void shouldFindByEmailIgnoreCaseAndFetchProfileByEmail() {
        // Given
        var profile = PassengerProfile.builder().phone("322 360 0901").countryCode("57").build();
        var passenger = Passenger.builder().fullName("Kevin Castillo").email("kevcast4@tucorazon.com").profile(profile).build();
        passengerRepository.save(passenger);

        // When
        Optional<Passenger> byEmail = passengerRepository.findByEmailIgnoreCase("kevcast4@tucorazon.com");
        Optional<Passenger> withProfile = passengerRepository.findByEmailWithProfile("KevCasT4@tucorazon.com");

        // Then
        assertThat(byEmail).isPresent();
        assertThat(byEmail.get().getEmail()).isEqualTo("kevcast4@tucorazon.com");
        assertThat(withProfile).isPresent();
        assertThat(withProfile.get().getProfile().getCountryCode()).isEqualTo("57");
    }
}
