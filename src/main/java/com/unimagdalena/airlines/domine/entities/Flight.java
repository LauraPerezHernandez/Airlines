package com.unimagdalena.airlines.domine.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "flights")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "flight_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String number;

    @Column(name = "departure_time",nullable = false)
    private OffsetDateTime departureTime;

    @Column(name = "arrival_time",nullable = false)
    private OffsetDateTime arrivalTime;

    @ManyToOne(optional = false)
    @JoinColumn(name = "airline_id")
    private Airline airline;

    @ManyToOne(optional = false)
    @JoinColumn(name = "origin_airport_id", nullable = false)
    private Airport origin;

    @ManyToOne(optional = false)
    @JoinColumn(name = "destination_airport_id", nullable = false)
    private Airport destination;

    @ManyToMany
    @JoinTable(name = "tags_flights",
    joinColumns = @JoinColumn(name = "flight_id"),
    inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @Builder.Default
    private Set<Tag> tags = new HashSet<>();

    public void addTag(Tag tag) {
        this.tags.add(tag);
        tag.getFlights().add(this);
    }
}
