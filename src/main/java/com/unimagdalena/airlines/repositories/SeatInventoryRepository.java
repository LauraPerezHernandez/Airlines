package com.unimagdalena.airlines.repositories;

import com.unimagdalena.airlines.entities.Cabin;
import com.unimagdalena.airlines.entities.SeatInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SeatInventoryRepository extends JpaRepository<SeatInventory,Long> {

    Optional<SeatInventory> findByFlight_IdAndCabin(Long flight_id, Cabin cabin);

    @Query("""
           SELECT CASE WHEN seatInv.availableSeats >= :min THEN TRUE ELSE FALSE END
           FROM SeatInventory seatInv
           WHERE seatInv.flight.id = :flightId
             AND seatInv.cabin = :cabin
           """)
    boolean hasAvailableSeats(@Param("flightId") Long flightId,
                              @Param("cabin") Cabin cabin,
                              @Param("min") Integer min);

}
