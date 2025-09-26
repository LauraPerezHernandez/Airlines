package com.unimagdalena.airlines.repositories;

import com.unimagdalena.airlines.entities.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Page<Booking> findByPassengerEmailIgnoreCaseOrderByCreatedAtDesc(String email, Pageable pageable);

    @Query("SELECT DISTINCT b FROM Booking b " +
            "LEFT JOIN FETCH b.items bi " +
            "LEFT JOIN FETCH bi.flight " +
            "LEFT JOIN FETCH b.passenger " +
            "WHERE b.id = :id")
    Optional<Booking> findByIdWithItemsAndFlightsAndPassenger(@Param("id") Long id);
}
