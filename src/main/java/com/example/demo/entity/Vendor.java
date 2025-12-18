package com.example.demo.entity;
import java.time.LocalDateTime;
import jakarta.persistence.*;


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


public Long getId() { return id; }
public void setId(Long id) { this.id = id; }
public String getVendorName() { return vendorName; }
public void setVendorName(String vendorName) { this.vendorName = vendorName; }
}