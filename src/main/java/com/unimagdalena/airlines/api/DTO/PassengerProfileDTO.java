package com.unimagdalena.airlines.api.DTO;

import java.io.Serializable;

public class PassengerProfileDTO {

    public record PassengerProfileCreateRequest(
            String phone,
            String countryCode
    ) implements Serializable {}

    public record PassengerProfileUpdateRequest(
            String phone,
            String countryCode
    ) implements Serializable {}

    public record PassengerProfileResponse(
            Long id,
            String phone,
            String countryCode
    ) implements Serializable {}
}

