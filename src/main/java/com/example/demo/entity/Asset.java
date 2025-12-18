package com.example.demo.entity;
import jakarta.persistence.*; // For @Entity, @Id, @GeneratedValue, @Column, etc.
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private String status;
    private LocalDateTime createdAt;

    @ManyToOne
    private Vendor vendor;

    @ManyToOne
    private DepreciationRule depreciationRule;

    // ✅ GETTERS & SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getPurchaseCost() { return purchaseCost; }
    public void setPurchaseCost(Double purchaseCost) { this.purchaseCost = purchaseCost; }

    public void setVendor(Vendor vendor) { this.vendor = vendor; }
    public void setDepreciationRule(DepreciationRule rule) { this.depreciationRule = rule; }

    public void setStatus(String status) { this.status = status; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
