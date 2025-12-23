package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "assets")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String assetTag;
    private String assetName;
    private LocalDate purchaseDate;
    private Double purchaseCost;
    private String status;
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name = "rule_id")
    private DepreciationRule depreciationRule;

    // --- THE CASCADE IS HERE ---
    @OneToOne(mappedBy = "asset", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("asset")
    private AssetDisposal disposal;

    @OneToMany(mappedBy = "asset", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("asset")
    private List<AssetLifecycleEvent> events;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAssetTag() { return assetTag; }
    public void setAssetTag(String assetTag) { this.assetTag = assetTag; }
    public String getAssetName() { return assetName; }
    public void setAssetName(String assetName) { this.assetName = assetName; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public AssetDisposal getDisposal() { return disposal; }
    public void setDisposal(AssetDisposal disposal) { this.disposal = disposal; }
    public List<AssetLifecycleEvent> getEvents() { return events; }
    public void setEvents(List<AssetLifecycleEvent> events) { this.events = events; }
}