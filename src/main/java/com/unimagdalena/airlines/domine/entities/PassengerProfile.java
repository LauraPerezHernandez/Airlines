package com.unimagdalena.airlines.domine.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "passenger_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PassengerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "passenger_profile_id")
    private Long id;

    @Column(nullable = false)
    private String phone;

    @Column(name ="country_code",nullable = false)
    private String countryCode;


}
