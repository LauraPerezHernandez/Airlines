package com.unimagdalena.airlines.api.DTO;

import java.io.Serializable;

public class PassengerDTO {

    public record PassengerCreateRequest(//cuando uses el PassengerCreateRequest, estarías creando tanto al pasajero como su perfil en la misma operación.
            String fullName,
            String email,
            PassengerProfileDto profile
    ) implements Serializable {}

    public record PassengerProfileDto(
            String phone,
            String countryCode
    ) implements Serializable {}

    public record PassengerUpdateRequest(
            String fullName,
            String email,
            PassengerProfileDto profile
    ) implements Serializable {}

    public record PassengerResponse(
            Long id,
            String fullName,
            String email,
            PassengerProfileDto profile
    ) implements Serializable {}
}
