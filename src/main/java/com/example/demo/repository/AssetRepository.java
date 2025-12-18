import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

package com.example.demo.repository;
import com.example.demo.entity.Asset;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    List<Asset> findByStatus(String status);
}
