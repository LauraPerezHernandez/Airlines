package com.unimagdalena.airlines.mapper;

import com.unimagdalena.airlines.api.DTO.AirportDTO;
import com.unimagdalena.airlines.domine.entities.Airport;

public class AirportMapper {

    // Convertir de entidad a DTO (response)
    public static AirportDTO.AirportResponse toResponse(Airport airport) {
        if (airport == null) {
            return null;
        }
        return new AirportDTO.AirportResponse(
                airport.getId(),
                airport.getCode(),
                airport.getName(),
                airport.getCity()
        );
    }

    // Convertir de DTO create a entidad
    public static Airport toEntity(AirportDTO.AirportCreateRequest dto) {
        if (dto == null) {
            return null;
        }
        Airport airport = new Airport();
        airport.setCode(dto.code());
        airport.setName(dto.name());
        airport.setCity(dto.city());
        return airport;
    }

    // Actualizar entidad desde DTO update
    public static void updateEntity(Airport airport, AirportDTO.AirportUpdateRequest dto) {
        if (dto == null || airport == null) {
            return;
        }
        airport.setCode(dto.code());
        airport.setName(dto.name());
        airport.setCity(dto.city());
    }
}

