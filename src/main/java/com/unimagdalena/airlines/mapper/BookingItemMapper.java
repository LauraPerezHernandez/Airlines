package com.unimagdalena.airlines.mapper;
import com.unimagdalena.airlines.domine.entities.BookingItem;
import com.unimagdalena.airlines.api.DTO.BookingItemDTO;
import com.unimagdalena.airlines.api.DTO.FlightDTO;
import com.unimagdalena.airlines.domine.entities.Flight;

public class BookingItemMapper {

    public static BookingItemDTO.BookingItemResponse toResponse(BookingItem item) {
        return new BookingItemDTO.BookingItemResponse(
                item.getId(),
                item.getCabin().name(),
                item.getPrice(),
                item.getSegmentOrder(),
                toFlightDto(item.getFlight())
        );
    }

    public static BookingItem toEntity(BookingItemDTO.BookingItemCreateRequest dto, Flight flight) {
        return BookingItem.builder()
                .cabin(Enum.valueOf(com.unimagdalena.airlines.domine.entities.Cabin.class, dto.cabin()))
                .price(dto.price())
                .segmentOrder(dto.segmentOrder())
                .flight(flight) // ya viene cargado desde el servicio
                .build();
    }

    public static void updateEntity(BookingItem item, BookingItemDTO.BookingItemUpdateRequest dto, Flight flight) {
        item.setCabin(Enum.valueOf(com.unimagdalena.airlines.domine.entities.Cabin.class, dto.cabin()));
        item.setPrice(dto.price());
        item.setSegmentOrder(dto.segmentOrder());
        item.setFlight(flight);
    }

    private static BookingItemDTO.FlightDto toFlightDto(Flight flight) {
        return new BookingItemDTO.FlightDto(
                flight.getId(),
                flight.getNumber(),
                toAirportDto(flight.getOrigin()),
                toAirportDto(flight.getDestination())
        );
    }
    private static BookingItemDTO.AirportDto toAirportDto(com.unimagdalena.airlines.domine.entities.Airport airport) {
        return new BookingItemDTO.AirportDto(
                airport.getId(),
                airport.getCode(),
                airport.getName(),
                airport.getCity()
        );
    }
}
