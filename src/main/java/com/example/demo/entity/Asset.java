package com.example.demo.entity;
import java.time.*;
import jakarta.persistence.*;


@Entity
public class Asset {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


@Column(unique = true)
private String assetTag;
private String assetName;
private LocalDate purchaseDate;
private Double purchaseCost;
private String status = "ACTIVE";
private LocalDateTime createdAt;


@ManyToOne
private Vendor vendor;


@ManyToOne
private DepreciationRule depreciationRule;


public Long getId() { return id; }
public void setId(Long id) { this.id = id; }
}



