package com.unimagdalena.airlines.domain.repositories;

import com.unimagdalena.airlines.entities.*;
import com.unimagdalena.airlines.repositories.BookingItemRepository;
import com.unimagdalena.airlines.repositories.BookingRepository;
import com.unimagdalena.airlines.repositories.FlightRepository;
import com.unimagdalena.airlines.repositories.PassengerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public class BookingRepositoryTest extends AbstractRepository {

    @Autowired private BookingRepository bookingRepository;

    @Autowired private PassengerRepository passengerRepository;

    @Autowired private FlightRepository flightRepository;

    @Autowired private BookingItemRepository bookingItemRepository;

    @Test
    @DisplayName("Booking: encuentra reservas de un pasajero paginadas y ordenadas por fecha desc")
    void shouldFindBookingsByPassengerEmailOrdered() {
        // GIVEN
        var passenger = passengerRepository.save(
                Passenger.builder()
                        .email("prueba@booking.com")
                        .fullName("prueba booking")
                        .profile(PassengerProfile.builder().phone("310 874 9874").countryCode("57").build())
                        .build()
        );


        var Booking1 = Booking.builder()
                .createdAt(OffsetDateTime.now().minusDays(10))
                .passenger(passenger)
                .build();
        bookingRepository.save(Booking1);


        var Booking2 = Booking.builder()
                .createdAt(OffsetDateTime.now())
                .passenger(passenger)
                .build();
        bookingRepository.save(Booking2);

        // WHEN
        Page<Booking> result = bookingRepository
                .findByPassengerEmailIgnoreCaseOrderByCreatedAtDesc("PRUEBA@BOOKING.COM", PageRequest.of(0, 10));

        // THEN
        assertThat(result.getTotalElements()).isEqualTo(2);
        assertThat(result.getContent().get(0).getId()).isEqualTo(Booking1.getId());
        assertThat(result.getContent().get(1).getId()).isEqualTo(Booking2.getId());
    }

    void findByIdWithItemsAndFlightsAndPassenger(){

        //given
        var passenger = passengerRepository.save(
                Passenger.builder()
                        .email("Prueba2@book.com")
                        .fullName("Pruba2 Book")
                        .profile(PassengerProfile.builder().phone("300 554 8743").countryCode("57").build())
                        .build()
        );

        var booking = bookingRepository.save(
                Booking.builder()
                        .createdAt(OffsetDateTime.now())
                        .passenger(passenger)
                        .build()
        );

        var flight1 = Flight.builder().number("J700").origin(Airport.builder().name("El Dorado").build()).destination(Airport.builder().name("Simon bolivar").build()).build();
        flightRepository.save(flight1);
        var flight2 = Flight.builder().number("PS600").origin(Airport.builder().name("Simon Bolivar").build()).destination(Airport.builder().name("El Dorado").build()).build();
        flightRepository.save(flight2);



        var item1 = BookingItem.builder().segmentOrder(1).cabin(Cabin.BUSINESS).price(new BigDecimal("500")).flight(flight1).booking(booking).build();
        bookingItemRepository.save(item1);
        var item2 = BookingItem.builder().segmentOrder(2).cabin(Cabin.BUSINESS).price(new BigDecimal("550")).flight(flight2).booking(booking).build();
        bookingItemRepository.save(item2);


        booking.setItems(List.of(item1,item2));

        bookingRepository.saveAndFlush(booking);

        //when

        Optional<Booking> result = bookingRepository.findByIdWithItemsAndFlightsAndPassenger(booking.getId());

        //then

        assertThat(result.isPresent()).isTrue();

        var loadedBooking = result.get();

        //pasajero precargado

        assertThat(loadedBooking.getPassenger().getEmail()).isEqualTo("Prueba2@book.com");
        assertThat(loadedBooking.getPassenger().getFullName()).isEqualTo("Prueba2 Book");

        // items precargado
        assertThat(loadedBooking.getItems()).hasSize(2);

        // Flights precargado

        assertThat(loadedBooking.getItems().get(0).getFlight().getNumber()).isEqualTo("F700");
        assertThat(loadedBooking.getItems().get(1).getFlight().getNumber()).isEqualTo("PS600");

    }
}

