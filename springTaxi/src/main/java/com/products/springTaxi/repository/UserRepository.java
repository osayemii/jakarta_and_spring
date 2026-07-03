package com.products.springTaxi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.products.springTaxi.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}
