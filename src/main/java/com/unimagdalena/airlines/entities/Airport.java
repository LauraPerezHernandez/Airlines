package com.unimagdalena.airlines.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "airports")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "airport_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String city;

    @OneToMany(mappedBy = "origin")
    @Builder.Default
    private List<Flight> departures = new ArrayList<>();

    @OneToMany(mappedBy = "destination")
    @Builder.Default
    private List<Flight> arrivals = new ArrayList<>();


    public void addDeparture(Flight flight) {
        departures.add(flight);
        flight.setOrigin(this);
    }

    public void addArrival(Flight flight) {
        arrivals.add(flight);
        flight.setDestination(this);
    }


}
