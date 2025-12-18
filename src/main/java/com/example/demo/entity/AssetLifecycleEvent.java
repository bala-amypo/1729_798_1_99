package com.example.demo.entity;
import jakarta.persistence.*; // For @Entity, @Id, @GeneratedValue, @Column, etc.
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
public class AssetLifecycleEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Asset asset;

    private String eventType;
    private String eventDescription;
    private LocalDate eventDate;
    private LocalDateTime loggedAt;

    public void setAsset(Asset asset) { this.asset = asset; }
    public void setLoggedAt(LocalDateTime loggedAt) { this.loggedAt = loggedAt; }
}
