package com.products.springTaxi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.products.springTaxi.entity.Ride;
import com.products.springTaxi.repository.RideRepository;

@Service
public class RideService {

    private final RideRepository rideRepository;

    public RideService(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    public Ride saveRide(Ride ride) {
        return rideRepository.save(ride);
    }

    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }

    public Ride getRideById(Long id) {
        return rideRepository.findById(id).orElse(null);
    }

    public Ride updateRide(Long id, Ride updatedRide) {

        Ride ride = rideRepository.findById(id).orElse(null);

        if (ride != null) {
            ride.setPassengerName(updatedRide.getPassengerName());
            ride.setPickUpLocation(updatedRide.gePpickUpLocation());
            ride.setDestination(updatedRide.getDestination());
            ride.setFare(updatedRide.getFare());
            ride.setStatus(updatedRide.getStatus());

            return rideRepository.save(ride);
        }

        return null;
    }

    public void deleteRide(Long id) {
        rideRepository.deleteById(id);
    }
}