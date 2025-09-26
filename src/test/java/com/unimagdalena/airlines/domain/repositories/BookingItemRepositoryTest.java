package com.unimagdalena.airlines.domain.repositories;

import com.unimagdalena.airlines.entities.*;
import com.unimagdalena.airlines.repositories.BookingItemRepository;
import com.unimagdalena.airlines.repositories.BookingRepository;
import com.unimagdalena.airlines.repositories.FlightRepository;
import com.unimagdalena.airlines.repositories.PassengerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public class BookingItemRepositoryTest extends AbstractRepository {

    @Autowired private BookingRepository bookingRepository;

    @Autowired private PassengerRepository passengerRepository;

    @Autowired private BookingItemRepository bookingItemRepository;

    @Autowired private FlightRepository flightRepository;

    @Test
    @DisplayName("BookingItem: lista segmentos ordenados")
    void shouldListItemsOrderedBySegmentOrder() {
        // GIVEN
        var passenger = passengerRepository.save(
                Passenger.builder()
                        .email("lausita@unimag.com")
                        .fullName("laura perez")
                        .profile(PassengerProfile.builder().phone("300 564 7689").countryCode("57").build())
                        .build()
        );

        var booking = bookingRepository.save(
                Booking.builder()
                        .createdAt(OffsetDateTime.now())
                        .passenger(passenger)
                        .build()
        );

        var flight1 = Flight.builder().number(" J678").origin(Airport.builder().name("El Dorado").build()).destination(Airport.builder().name("Jose Maria Cordova").build()).build();
        flightRepository.saveAndFlush(flight1);
        var flight2 = Flight.builder().number("A506").origin(Airport.builder().name("Simon Bolivar").build()).destination(Airport.builder().name("Olaya Herrera").build()).build();
        flightRepository.saveAndFlush(flight2);


        var item2 = bookingItemRepository.saveAndFlush(
                BookingItem.builder()
                        .segmentOrder(2)
                        .cabin(Cabin.BUSINESS)
                        .price(new BigDecimal("800"))
                        .booking(booking)
                        .flight(flight2)
                        .build()
        );

        var item1 = bookingItemRepository.saveAndFlush(
                BookingItem.builder()
                        .segmentOrder(1)
                        .cabin(Cabin.ECONOMY)
                        .price(new BigDecimal("100"))
                        .booking(booking)
                        .flight(flight1)
                        .build()
        );

        // WHEN
        List<BookingItem> items = bookingItemRepository.findByBookingIdOrderBySegmentOrder(booking.getId());

        // THEN
        assertThat(items).hasSize(2);
        assertThat(items.get(0).getSegmentOrder()).isEqualTo(1);
        assertThat(items.get(1).getSegmentOrder()).isEqualTo(2);
    }

    @Test
    @DisplayName("BookingItem: suma precios de los items de una reserva")
    void shouldSumPriceByBookingId() {
        // GIVEN
        var passenger = passengerRepository.save(
                Passenger.builder()
                        .email("ValeB@unimag.com")
                        .fullName("Vale Bonilla")
                        .profile(PassengerProfile.builder().phone("322 567 398").countryCode("57").build())
                        .build()
        );

        var booking = bookingRepository.save(
                Booking.builder()
                        .createdAt(OffsetDateTime.now())
                        .passenger(passenger)
                        .build()
        );

        var flight = Flight.builder().number("J700").origin(Airport.builder().name("El Dorado").build()).destination(Airport.builder().name("Jose Maria Cordova").build()).build();
        flightRepository.saveAndFlush(flight);


        bookingItemRepository.saveAll(List.of(
                BookingItem.builder().segmentOrder(1).cabin(Cabin.ECONOMY).price(new BigDecimal("170")).booking(booking).flight(flight).build(),
                BookingItem.builder().segmentOrder(2).cabin(Cabin.BUSINESS).price(new BigDecimal("450")).booking(booking).flight(flight).build()
        ));

        // WHEN
        BigDecimal total = bookingItemRepository.sumPriceByBookingId(booking.getId());

        // THEN
        assertThat(total).isEqualByComparingTo("500");
    }

    @Test
    @DisplayName("BookingItem: cuenta asientos por vuelo y cabina")
    void shouldCountSeatsByFlightAndCabin() {
        // GIVEN
        var passenger = passengerRepository.save(
                Passenger.builder()
                        .email("kevin@prueba.com")
                        .fullName("Kevin Castillo")
                        .profile(PassengerProfile.builder().phone("300 456 296").countryCode("57").build())
                        .build()
        );

        var booking = bookingRepository.save(
                Booking.builder()
                        .createdAt(OffsetDateTime.now())
                        .passenger(passenger)
                        .build()
        );

        var flight = Flight.builder().number("J900").origin(Airport.builder().name("El Dorado").build()).destination(Airport.builder().name("Jose Maria Cordova").build()).build();
        flightRepository.saveAndFlush(flight);

        bookingItemRepository.saveAll(List.of(
                BookingItem.builder().segmentOrder(1).cabin(Cabin.ECONOMY).price(new BigDecimal("160")).booking(booking).flight(flight).build(),
                BookingItem.builder().segmentOrder(2).cabin(Cabin.ECONOMY).price(new BigDecimal("180")).booking(booking).flight(flight).build(),
                BookingItem.builder().segmentOrder(3).cabin(Cabin.BUSINESS).price(new BigDecimal("600")).booking(booking).flight(flight).build()
        ));

        // WHEN
        long economyCount = bookingItemRepository.countByFlightIdAndCabin(flight.getId(), Cabin.ECONOMY);
        long businessCount = bookingItemRepository.countByFlightIdAndCabin(flight.getId(), Cabin.BUSINESS);

        // THEN
        assertThat(economyCount).isEqualTo(2);
        assertThat(businessCount).isEqualTo(1);
    }

}
