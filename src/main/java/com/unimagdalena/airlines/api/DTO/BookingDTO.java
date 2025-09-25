package com.unimagdalena.airlines.api.DTO;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

public class BookingDTO{

    public record BookingCreateRequest(
            Long passengerId,
            List<BookingItemDto> items
    ) implements Serializable {}

    public record BookingUpdateRequest(
            List<BookingItemDto> items
    ) implements Serializable {}

    public record BookingItemDto(
            Long id,
            String seatNumber,
            Long flightId
    ) implements Serializable {}

    public record BookingResponse(
            Long id,
            OffsetDateTime createdAt,
            OffsetDateTime updatedAt,
            PassengerSummaryDto passenger,
            List<BookingItemDto> items
    ) implements Serializable {}

    public record PassengerSummaryDto(
            Long id,
            String name,
            String email
    ) implements Serializable {}
}

