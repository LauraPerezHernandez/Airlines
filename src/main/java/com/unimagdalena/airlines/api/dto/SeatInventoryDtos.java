package com.unimagdalena.airlines.api.dto;

import com.unimagdalena.airlines.entities.Cabin;

import java.io.Serializable;

public class SeatInventoryDtos {

    public record SeatInventoryRequest(Cabin cabin, Integer availableSeats, long flightId) implements Serializable {}
    public record SeatInventoryUpdate(Cabin cabin, Integer availableSeats, long flightId) implements Serializable {}
    public record SeatInventoryResponse(Long id, Cabin cabin, Integer totalSeats, Integer availableSeats, Long flightId) implements Serializable {}
}
