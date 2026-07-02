package com.products.springTaxi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.products.springTaxi.entity.Ride;

public interface RideRepository extends JpaRepository<Ride, Long> {
    
    List<Ride> findByStatus(String status);
    
}