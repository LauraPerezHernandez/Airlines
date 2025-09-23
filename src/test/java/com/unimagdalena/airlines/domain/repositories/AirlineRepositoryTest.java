package com.unimagdalena.airlines.domain.repositories;

import com.unimagdalena.airlines.entities.Airline;
import com.unimagdalena.airlines.repositories.AirlineRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

public class AirlineRepositoryTest extends AbstractRepository {
    @Autowired AirlineRepository airlineRepository;

    @Test @DisplayName("Airline: find by code")
    void shouldFindByCode() {

        //Given
        airlineRepository.save(Airline.builder().code("AV").name("avianca").build());
        airlineRepository.save(Airline.builder().code("WN").name("wingo").build());

        //When
        Optional<Airline> airline = airlineRepository.findByCode("AV");

        //Then
        assertThat(airline).isPresent();
    }
}
