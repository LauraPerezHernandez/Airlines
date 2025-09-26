package com.unimagdalena.airlines.mapper;

import com.unimagdalena.airlines.api.DTO.BookingDTO;
import com.unimagdalena.airlines.domine.entities.Booking;
import com.unimagdalena.airlines.domine.entities.BookingItem;
import com.unimagdalena.airlines.domine.entities.Passenger;


import java.time.OffsetDateTime;
import java.util.List;

public class BookingMapper {
    
    public static Booking toEntity(BookingDTO.BookingCreateRequest dto, Passenger passenger, List<BookingItem> items) {
        return Booking.builder()
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                .passenger(passenger)
                .items(items)
                .build();
    }

    public static void updateEntity(Booking booking, BookingDTO.BookingUpdateRequest dto, List<BookingItem> items) {
        booking.setUpdatedAt(OffsetDateTime.now());
        booking.setItems(items);
        if (items != null) {
            items.forEach(i -> i.setBooking(booking));
        }
    }


    private static BookingDTO.PassengerSummaryDto toPassengerSummary(Passenger passenger) {
        return new BookingDTO.PassengerSummaryDto(
                passenger.getId(),
                passenger.getFullName(),
                passenger.getEmail()
        );
    }
}
