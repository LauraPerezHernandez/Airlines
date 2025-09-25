package com.unimagdalena.airlines.api.DTO;

import java.io.Serializable;
import java.util.List;

public class AirlineDTO {

    public record AirlineCreateRequest(
            String code,
            String name
    ) implements Serializable {}

    public record AirlineUpdateRequest(
            String code,
            String name
    ) implements Serializable {}

    public record AirlineResponse(
            Long id,
            String code,
            String name,
            List<FlightInfoDto> flights
    ) implements Serializable {}

    public record FlightInfoDto(
            Long flightId,
            String flightNumber,
            String origin,
            String destination
    ) implements Serializable {}
}
