package com.unimagdalena.airlines.repositories;

import com.unimagdalena.airlines.entities.PassengerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassengerProfileRepository extends JpaRepository<PassengerProfile,Long> {

    List<PassengerProfile> findByCountryCode(String countryCode);
}
