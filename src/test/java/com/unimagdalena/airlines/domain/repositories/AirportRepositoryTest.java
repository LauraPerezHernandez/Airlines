package com.unimagdalena.airlines.domain.repositories;

import com.unimagdalena.airlines.entities.Airport;
import com.unimagdalena.airlines.repositories.AirportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.objectweb.asm.Opcodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


public class AirportRepositoryTest extends AbstractRepository {
    @Autowired AirportRepository airportRepository;
    @Autowired private TestEntityManager testEntityManager;

    @Test @DisplayName("Airport: find by code")
    void findByCode() {
        // GIVEN
        airportRepository.save(Airport.builder().code("BOG").name("El Dorado").city("Bogotá").build());
        airportRepository.save(Airport.builder().code("SMT").name("Simon Bolivar").city("Santa Marta").build());

        // WHEN
        Optional<Airport> airport1 = airportRepository.findByCode("BOG");
        Optional<Airport> airport2 = airportRepository.findByCode("XRG");

        // THEN
        assertThat(airport1).isPresent();
        assertThat(airport1.get().getName()).isEqualTo("El Dorado");
        assertThat(airport2).isEmpty();
    }

}

