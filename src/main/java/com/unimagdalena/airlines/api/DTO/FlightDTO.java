package com.unimagdalena.airlines.api.DTO;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Set;

public class FlightDTO {

    public record FlightCreateRequest(
            String number,
            OffsetDateTime departureTime,
            OffsetDateTime arrivalTime,
            AirlineDto airline,
            AirportDto origin,
            AirportDto destination,
            Set<TagDto> tags
    ) implements Serializable {}

    public record FlightUpdateRequest(
            String number,
            OffsetDateTime departureTime,
            OffsetDateTime arrivalTime,
            AirlineDto airline,
            AirportDto origin,
            AirportDto destination,
            Set<TagDto> tags
    ) implements Serializable {}

    public record FlightResponse(
            Long id,
            String number,
            OffsetDateTime departureTime,
            OffsetDateTime arrivalTime,
            AirlineDto airline,
            AirportDto origin,
            AirportDto destination,
            Set<TagDto> tags
    ) implements Serializable {}

    public record AirlineDto(
            Long id,
            String code,
            String name
    ) implements Serializable {}

    public record AirportDto(
            Long id,
            String code,
            String name,
            String city
    ) implements Serializable {}

    public record TagDto(
            Long id,
            String name
    ) implements Serializable {}
}

