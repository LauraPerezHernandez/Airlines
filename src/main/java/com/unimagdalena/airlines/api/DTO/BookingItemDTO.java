package com.unimagdalena.airlines.api.DTO;

import java.math.BigDecimal;
import java.io.Serializable;

public class BookingItemDTO {
    public record BookingItemCreateRequest(
            String cabin,
            BigDecimal price,
            Integer segmentOrder,
            Long flightId
    ) implements Serializable {}

    public record BookingItemUpdateRequest(
            String cabin,
            BigDecimal price,
            Integer segmentOrder,
            Long flightId
    ) implements Serializable {}

    public record BookingItemResponse(
            Long id,
            String cabin,
            BigDecimal price,
            Integer segmentOrder,
            FlightDto flight
    ) implements Serializable {}

    public record FlightDto(
            Long id,
            String number,
            AirportDto origin,
            AirportDto destination
    ) implements Serializable {}

    public record AirportDto(
            Long id,
            String code,
            String name,
            String city
    ) implements Serializable {}
}
