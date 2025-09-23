package com.unimagdalena.airlines.repositories;

import com.unimagdalena.airlines.entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport,Long> {

    //- Recupera un aeropuerto por su código IATA (p. ej., “BOG”).
    Optional<Airport> findByCode(String code);

    //-Recupera un aeropuerto por su ciudad
    List<Airport> findByCity(String city);
}
