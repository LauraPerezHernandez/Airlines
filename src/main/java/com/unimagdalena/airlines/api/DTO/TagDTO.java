package com.unimagdalena.airlines.api.DTO;

import java.io.Serializable;
import java.util.Set;

public class TagDTO {

    public record TagCreateRequest(
            String name
    ) implements Serializable {}

    public record TagUpdateRequest(
            String name
    ) implements Serializable {}

    public record TagResponse(
            Long id,
            String name,
            Set<FlightDto> flights
    ) implements Serializable {}

    public record FlightDto(
            Long id,
            String number
    ) implements Serializable {}
}

