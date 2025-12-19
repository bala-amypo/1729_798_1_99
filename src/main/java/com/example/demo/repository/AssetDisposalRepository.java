package com.example.demo.repository;
import com.example.demo.entity.AssetDisposal;
import com.example.demo.entity.Asset;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AssetDisposalRepository extends JpaRepository<Asset, Long> {
}
