package com.example.demo.entity;
import java.time.*;
import jakarta.persistence.*;


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
}