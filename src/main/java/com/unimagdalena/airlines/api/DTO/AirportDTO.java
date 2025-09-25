package com.unimagdalena.airlines.api.DTO;

import java.io.Serializable;

public class AirportDTO {

    public record AirportCreateRequest(
            String code,
            String name,
            String city
    ) implements Serializable {}

    public record AirportUpdateRequest(
            String code,
            String name,
            String city
    ) implements Serializable {}

    public record AirportResponse(
            Long id,
            String code,
            String name,
            String city
    ) implements Serializable {}
}
