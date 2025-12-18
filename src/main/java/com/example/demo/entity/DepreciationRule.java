package com.example.demo.entity;
import java.time.LocalDateTime;
import jakarta.persistence.*;


@Entity
public class DepreciationRule {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


@Column(unique = true)
private String ruleName;
private String method;
private Integer usefulLifeYears;
private Double salvageValue;
private LocalDateTime createdAt;


public Long getId() { return id; }
public void setId(Long id) { this.id = id; }
}