package com.unimagdalena.airlines.domine.entities.repositories;

import com.unimagdalena.airlines.domine.entities.PassengerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassengerProfileRepository extends JpaRepository<PassengerProfile,Long> {

    List<PassengerProfile> findByCountryCode(String countryCode);
}
