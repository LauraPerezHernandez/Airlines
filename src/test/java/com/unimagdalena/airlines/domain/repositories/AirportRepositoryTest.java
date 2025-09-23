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

    @BeforeEach
    public void setUp() {
        Airport airport_1 = Airport.builder().code("BOG").city("bogota").name("el dorado").build();
        Airport airport_2 = Airport.builder().code("SMT").city("santa marta").name("simon bolivar").build();
        Airport airport_3 = Airport.builder().code("MDL").city("medellin").name("jose maria cordova").build();
        Airport airport_4 = Airport.builder().code("BQL").city("barranquilla").name("ernesto cortissoz").build();

        testEntityManager.persistAndFlush(airport_1);
        testEntityManager.persistAndFlush(airport_2);
        testEntityManager.persistAndFlush(airport_3);
        testEntityManager.persistAndFlush(airport_4);
    }

    @Test @DisplayName("Airport: find by code")
    void findByCode() {
        //When
        Optional<Airport> loaded = airportRepository.findByCode("SMT");
        // /Then
        assertThat(loaded).isPresent();
        assertThat(loaded.get().getCode()).isEqualTo("SMT");
    }

    @Test @DisplayName("Airport: find by city")
    void findByCodeAndCity() {
        //When
        List<Airport> loaded = airportRepository.findByCity("BOG");

        //Then
        assertThat(airportRepository.findByCity("bogota")).isNotEmpty();
    }
}

