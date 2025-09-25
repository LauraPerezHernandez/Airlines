package com.unimagdalena.airlines.api.DTO;

import java.math.BigDecimal;

public class BookingItemDTO {
    private final String cabin;
    private final BigDecimal price;
    private final Integer segmentOrder;
    private final String flightOrigin;
    private final String flightDestination;

    public BookingItemDTO(String cabin, BigDecimal price, Integer segmentOrder,
                          String flightOrigin, String flightDestination) {
        this.cabin = cabin;
        this.price = price;
        this.segmentOrder = segmentOrder;
        this.flightOrigin = flightOrigin;
        this.flightDestination = flightDestination;
    }

    public String getCabin() {
        return cabin;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getSegmentOrder() {
        return segmentOrder;
    }

    public String getFlightOrigin() {
        return flightOrigin;
    }

    public String getFlightDestination() {
        return flightDestination;
    }
}
