package com.unimagdalena.airlines.services.mapper;

import com.unimagdalena.airlines.api.dto.BookingDtos;
import com.unimagdalena.airlines.api.dto.BookingItemDtos;
import com.unimagdalena.airlines.entities.Booking;
import com.unimagdalena.airlines.entities.BookingItem;
import com.unimagdalena.airlines.entities.Cabin;
import com.unimagdalena.airlines.entities.Flight;

public class BookingItemMapper {

    private final FlightMapper flightMapper;


    public BokingItemMapper(FlightMapper flightMapper) {
        this.flightMapper = flightMapper;
    }

    public static BookingItem toEntity(BookingItemDtos.BookingItemCreateRequest request, Flight flight) {
        if (request == null) return null;
        if (flight == null) throw new IllegalArgumentException("flight is null");

        return BookingItem.builder()
                .price(request.price())
                .segmentOrder(request.segmentOrder())
                .cabin(request.cabin())
                .flight(flight)
                .build();
    }

    public static void updateEntity(BookingItem item, BookingItemDtos.BookingItemUpdateRequest request, Flight flight) {
        if (item == null || request == null ) return;
        if (request.price() != null) {
            item.setPrice(request.price());
        }
        if (request.segmentOrder() != null) {
            item.setSegmentOrder(request.segmentOrder());
        }
        if (request.cabin() != null) {
            item.setCabin(request.cabin());
        }
        if (flight != null) {
            item.setFlight(flight);
        }

    }

    public  BookingItemDtos.BookingItemResponse toResponse(BookingItem item) {
        if (item == null) return null;
        return new BookingItemDtos.BookingItemResponse(
                item.getId(), item.getPrice(), item.getSegmentOrder(),
                item.getCabin(),flightMapper.toResponse(item.getFlight())
        );

    }

}
