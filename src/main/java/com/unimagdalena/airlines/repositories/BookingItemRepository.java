package com.unimagdalena.airlines.repositories;

import com.unimagdalena.airlines.entities.BookingItem;
import com.unimagdalena.airlines.entities.Cabin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface BookingItemRepository extends JpaRepository<BookingItem, Long> {

    //- lista los segmentos (tramos) de una reserva, ordenados por segmentOrder (1,2,3…).
    List<BookingItem> findByBookingIdOrderBySegmentOrder(Long bookingId);

    //- calcula el total de la reserva sumando los precios de sus ítems. (JPQL con SUM + COALESCE)
    @Query("SELECT COALESCE(SUM(bi.price), 0) FROM BookingItem bi WHERE bi.booking.id = :bookingId")
    BigDecimal sumPriceByBookingId(@Param("bookingId") Long bookingId);

    //- cuenta cuántos asientos han sido vendidos/reservados para un vuelo y cabina. (JPQL con COUNT)
    @Query("SELECT COUNT(bi) FROM BookingItem bi WHERE bi.flight.id = :flightId AND bi.cabin = :cabin")
    long countByFlightIdAndCabin(@Param("flightId") Long flightId, @Param("cabin") Cabin cabin);
}
