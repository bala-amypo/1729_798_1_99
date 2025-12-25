package com.example.demo.repository;

import com.example.demo.entity.Vendor;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Asset;
import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    List<Asset> findByStatus(String status);
    List<Asset> findByVendor(Vendor vendor);
boolean existsByAssetTag(String assetTag);

}
