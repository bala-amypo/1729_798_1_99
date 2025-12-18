package com.example.demo.repository;

import com.example.demo.entity.AssetLifecycleEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AssetLifecycleEventRepository extends JpaRepository<AssetLifecycleEvent, Long> {
    // Add this line to fix the "findByAssetId" error
    List<AssetLifecycleEvent> findByAssetId(Long assetId);
}