package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "departures") // Specify the table name here
public class Departure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String airlines;
    private String flightNumber;
    private String sourceCity;
    private String destinationCity;
    private String startTime; // Modified to String type
    private String duration;
    private int numberOfSeats;

    public Departure() {}

    public Departure(Long id, String airlines,String flightNumber, String sourceCity, String destinationCity, String startTime, String duration, int numberOfSeats) {
        this.id = id;
        this.airlines = airlines;
        this.sourceCity = sourceCity;
        this.destinationCity = destinationCity;
        this.startTime = startTime;
        this.duration = duration;
        this.numberOfSeats = numberOfSeats;
    }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setAirlines(String airlines) { this.airlines = airlines; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
    public void setSourceCity(String sourceCity) { this.sourceCity = sourceCity; }
    public void setDestinationCity(String destinstionCity) { this.destinationCity = destinstionCity; }
    public void setStartTime(String startTime) { this.startTime = startTime; }
    public void setDuration(String duration) { this.duration = duration; }
    public void setNumberOfSeats(int numberOfSeats) { this.numberOfSeats = numberOfSeats; }

    // Getters
    public Long getId() { return id; }
    public String getAirlines() { return airlines; }
    public String getFlightNumber() { return flightNumber; }
    public String getSourceCity() { return sourceCity; }
    public String getDestinationCity() { return destinationCity; }
    public String getStartTime() { return startTime; }
    public String getDuration() { return duration; }
    public int getNumberOfSeats() { return numberOfSeats; }
}