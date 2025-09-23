package com.unimagdalena.airlines.repositories;

import com.unimagdalena.airlines.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    // Busca pasajero por email, ignorando mayúsculas/minúsculas
    Optional<Passenger> findByEmailIgnoreCase(String email);

    // Busca pasajero por email con fetch profile
    @Query("SELECT p FROM Passenger p LEFT JOIN FETCH p.profile WHERE LOWER(p.email) = LOWER(:email)")
    Optional<Passenger> findByEmailWithProfile(@Param("email") String email);
}
