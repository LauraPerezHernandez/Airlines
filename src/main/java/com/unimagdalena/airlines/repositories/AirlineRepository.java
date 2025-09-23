package com.unimagdalena.airlines.repositories;

import com.unimagdalena.airlines.entities.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirlineRepository extends JpaRepository<Airline, Long> {

    //- Recupera una aerolínea por su código IATA (p. ej., “AV”).
    Optional<Airline> findByCode(String code);
}
