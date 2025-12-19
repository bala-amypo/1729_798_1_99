package com.example.demo.repository;
import com.example.demo.entity.Asset;
import com.example.demo.entity.Vendor;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VendorRepository extends JpaRepository<Asset, Long> {
}
