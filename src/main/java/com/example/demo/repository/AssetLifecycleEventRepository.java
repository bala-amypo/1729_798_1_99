package com.example.demo.repository;

import com.example.demo.entity.AssetLifecycleEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetLifecycleEventRepository extends JpaRepository<AssetLifecycleEvent, Long> {
}