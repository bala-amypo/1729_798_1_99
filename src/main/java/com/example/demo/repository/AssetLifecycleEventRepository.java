public interface AssetLifecycleEventRepository 
        extends JpaRepository<AssetLifecycleEvent, Long> {

    List<AssetLifecycleEvent> findByAssetId(Long assetId);
}
