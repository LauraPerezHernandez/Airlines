package com.unimagdalena.airlines.api.DTO;

import java.io.Serializable;

public class SeatInventoryDTO {

    public record SeatInventoryCreateRequest(
            String cabin,
            Integer totalSeats,
            Integer availableSeats,
            Long flightId
    ) implements Serializable {}

    public record SeatInventoryUpdateRequest(
            String cabin,
            Integer totalSeats,
            Integer availableSeats,
            Long flightId
    ) implements Serializable {}

    public record SeatInventoryResponse(
            Long id,
            String cabin,
            Integer totalSeats,
            Integer availableSeats,
            FlightDto flight
    ) implements Serializable {}

    public record FlightDto(
            Long id,
            String number
    ) implements Serializable {}
}



