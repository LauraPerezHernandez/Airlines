package com.unimagdalena.airlines.domine.entities.repositories;

import com.unimagdalena.airlines.domine.entities.Cabin;
import com.unimagdalena.airlines.domine.entities.SeatInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeatInventoryRepository extends JpaRepository<SeatInventory,Long> {

    Optional<SeatInventory> findByFlight_IdAndCabin(Long flight_id, Cabin cabin);

    boolean existsByFlight_IdAndCabinAndAvailableSeatsIsGreaterThanEqual(Long flight_Id, Cabin cabin, Integer min);
}
