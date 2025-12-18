package com.example.demo.entity;
import jakarta.persistence.*; // For @Entity, @Id, @GeneratedValue, @Column, etc.
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String vendorName;
    private String contactEmail;
    private String phone;
    private LocalDateTime createdAt;

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
