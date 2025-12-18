package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Asset;

public interface AssetLifecycleEventRepository extends JpaRepository<Asset, Long> {
}
