package com.unimagdalena.airlines.api.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

public class BookingDtos {

    public  record BookingCreateRequest(OffsetDateTime createdAt,Long passengerId ) implements Serializable {}
    public  record BookingUpdateRequest(Long id, Long passenger_id) implements Serializable {}
    public  record BookingResponse(Long id, OffsetDateTime createdAt, PassengerDtos.PassengerResponse passengerDto) implements Serializable {}
}
