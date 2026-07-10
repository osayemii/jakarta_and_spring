package com.example.studentmanager.model;

import jakarta.persistence.*;

/**
 * Represents an application user stored in the database.
 * Spring Security will load this via UserDetailsServiceImpl.
 * Default admin user is seeded in DataInitializer.
 */
@Entity
@Table(name = "app_users")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password; // stored as BCrypt hash

    @Column(nullable = false)
    private String role; // e.g. "ROLE_ADMIN"

    public AppUser() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
