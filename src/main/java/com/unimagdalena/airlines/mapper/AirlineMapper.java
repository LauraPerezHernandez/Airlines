package com.unimagdalena.airlines.mapper;

import com.unimagdalena.airlines.api.DTO.AirlineDTO;
import com.unimagdalena.airlines.domine.entities.Airline;

import java.util.List;
import java.util.stream.Collectors;

public class AirlineMapper {

    public static Airline toEntity(AirlineDTO.AirlineCreateRequest dto) {
        return Airline.builder()
                .code(dto.code())
                .name(dto.name())
                .build();
    }

    public static Airline toEntity(AirlineDTO.AirlineUpdateRequest dto, Airline airline) {
        airline.setCode(dto.code());
        airline.setName(dto.name());
        return airline;
    }

    public static AirlineDTO.AirlineResponse toResponse(Airline airline) {
        List<AirlineDTO.FlightInfoDto> flights = airline.getFlights() == null ? List.of() :
                airline.getFlights().stream()
                        .map(f -> new AirlineDTO.FlightInfoDto(
                                f.getId(),
                                f.getNumber(),
                                f.getOrigin().getName(),
                                f.getDestination().getName()
                        ))
                        .collect(Collectors.toList());

        return new AirlineDTO.AirlineResponse(
                airline.getId(),
                airline.getCode(),
                airline.getName(),
                flights
        );
    }
}

