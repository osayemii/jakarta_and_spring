package com.products.springTaxi.entity;

import jakarta.persistence.*;

@Entity
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passengerName;
    private String pickUpLocation;
    private String destination;
    private String fare;
    private String status;

    Ride() {
    }

    Ride(Long id, String passengerName, String pickUpLocation, String destination, String fare, String status) {
        this.id = id;
        this.passengerName = passengerName;
        this.pickUpLocation = pickUpLocation;
        this.destination = destination;
        this.fare = fare;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String gePpickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(String pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFare() {
        return fare;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
