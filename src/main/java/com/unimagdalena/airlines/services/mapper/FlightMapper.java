package com.unimagdalena.airlines.services.mapper;

import com.unimagdalena.airlines.api.dto.FlightDtos;
import com.unimagdalena.airlines.api.dto.TagDtos;
import com.unimagdalena.airlines.entities.Airline;
import com.unimagdalena.airlines.entities.Airport;
import com.unimagdalena.airlines.entities.Flight;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class FlightMapper {

    public Flight toEntity(FlightDtos.FlightCreateRequest dto) {
        if (dto == null) return null;
        Flight entity = new Flight();
        entity.setNumber(dto.number());
        entity.setDepartureTime(dto.departureTime());
        entity.setArrivalTime(dto.arrivalTime());
        // Resto de atributos en services
        return entity;
    }

    public void toUpdateEntity(FlightDtos.FlightUpdateRequest dto, Flight entity) {
        if (dto == null || entity == null) return;
        entity.setNumber(dto.number());
        entity.setDepartureTime(dto.departureTime());
        entity.setArrivalTime(dto.arrivalTime());
        // Resto de atributos en services
    }

    public FlightDtos.FlightResponse toResponse(Flight entity) {
        if (entity == null) return null;

        FlightDtos.AirlineDto airlineRef = null;
        if (entity.getAirline() != null) {
            Airline a = entity.getAirline();
            airlineRef = new FlightDtos.AirlineDto(a.getId(), a.getCode(), a.getName());
        }

        FlightDtos.AirportDto originRef = null;
        if (entity.getOrigin() != null) {
            Airport o = entity.getOrigin();
            originRef = new FlightDtos.AirportDto(o.getId(), o.getCode(), o.getCity());
        }

        FlightDtos.AirportDto destinationRef = null;
        if (entity.getDestination() != null) {
            Airport d = entity.getDestination();
            destinationRef = new FlightDtos.AirportDto(d.getId(), d.getCode(), d.getCity());
        }

        Set<FlightDtos.TagDto> tagRefs = Set.of();
        if (entity.getTags() != null) {
            tagRefs = entity.getTags().stream()
                    .filter(Objects::nonNull)
                    .map(t -> new FlightDtos.TagDto(t.getId(), t.getName()))
                    .collect(Collectors.toSet());
        }

        return new FlightDtos.FlightResponse(
                entity.getId(),
                entity.getNumber(),
                entity.getDepartureTime(),
                entity.getArrivalTime(),
                airlineRef,
                originRef,
                destinationRef,
                tagRefs
        );
    }
}
