package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "asset_disposals")
public class AssetDisposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // RELATION: Many-to-One or One-to-One
    // We use @OneToOne because an asset is disposed of only once.
    // The "JoinColumn" connects this table to the "assets" table.
    @OneToOne
    @JoinColumn(name = "asset_id", referencedColumnName = "id", nullable = false, unique = true)
    @JsonIgnoreProperties({"disposal", "events", "hibernateLazyInitializer", "handler"}) 
    private Asset asset;

    private String disposalMethod;
    private Double disposalValue;
    private LocalDate disposalDate;

    // RELATION: Many disposals can be approved by one Admin User.
    // No Cascade here; deleting a record shouldn't delete the User.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "approved_by_id", nullable = false)
    @JsonIgnoreProperties({"password", "roles"})
    private User approvedBy;

    private LocalDateTime createdAt = LocalDateTime.now();

    // Getters and Setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Asset getAsset() { return asset; }
    public void setAsset(Asset asset) { this.asset = asset; }
    public String getDisposalMethod() { return disposalMethod; }
    public void setDisposalMethod(String disposalMethod) { this.disposalMethod = disposalMethod; }
    public Double getDisposalValue() { return disposalValue; }
    public void setDisposalValue(Double disposalValue) { this.disposalValue = disposalValue; }
    public LocalDate getDisposalDate() { return disposalDate; }
    public void setDisposalDate(LocalDate disposalDate) { this.disposalDate = disposalDate; }
    public User getApprovedBy() { return approvedBy; }
    public void setApprovedBy(User approvedBy) { this.approvedBy = approvedBy; }
}