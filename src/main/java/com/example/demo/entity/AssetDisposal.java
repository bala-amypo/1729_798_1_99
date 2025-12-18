package com.example.demo.entity;
import java.time.*;
import jakarta.persistence.*;


@Entity
public class AssetDisposal {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


@OneToOne
private Asset asset;
private String disposalMethod;
private Double disposalValue;
private LocalDate disposalDate;


@ManyToOne
private User approvedBy;
private LocalDateTime createdAt;
}