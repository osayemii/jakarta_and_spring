package com.products.springTaxi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.products.springTaxi.entity.*;
import com.products.springTaxi.service.RideService;

@RestController
@RequestMapping("/rides")
public class RideController {

	private final RideService rideService;

	public RideController(RideService rideService) {
		this.rideService = rideService;
	}

	@PostMapping
	public Ride createRide(@RequestBody Ride ride) {
		ride.setId(null);
		return rideService.saveRide(ride);
	}

	@GetMapping
	public List<Ride> getAllRides() {
		return rideService.getAllRides();
	}

	@GetMapping("/{id}")
	public Ride getRideById(@PathVariable Long id) {
		return rideService.getRideById(id);
	}

	@PutMapping("/{id}")
	public Ride updateRide(@PathVariable Long id, @RequestBody Ride ride) {
		return rideService.updateRide(id, ride);
	}

	@DeleteMapping("/{id}")
	public void deleteRide(@PathVariable Long id) {
		rideService.deleteRide(id);
	}
}