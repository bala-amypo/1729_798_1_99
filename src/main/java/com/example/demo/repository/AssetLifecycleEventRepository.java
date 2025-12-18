import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

package com.example.demo.repository;
import com.example.demo.entity.AssetLifecycleEvent;

public interface AssetLifecycleEventRepository 
        extends JpaRepository<AssetLifecycleEvent, Long> {

    List<AssetLifecycleEvent> findByAssetId(Long assetId);
}
