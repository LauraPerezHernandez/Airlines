package com.unimagdalena.airlines.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "seats_inventory")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SeatInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "seat_inventory_id")
    private Long id;

    @Column(nullable = false)
    private Cabin cabin;

    @Column(name = "total_seats",nullable = false)
    private Integer totalSeats;

    @Column(name = "available_seats", nullable = false)
    private Integer availableSeats;

    @ManyToOne @JoinColumn(name = "flight_id",nullable = false)
    private Flight flight;
}
