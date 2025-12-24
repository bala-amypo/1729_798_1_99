package com.example.demo.service;

import com.example.demo.entity.AssetDisposal;
import com.example.demo.entity.User;
import java.util.List;

public interface AssetDisposalService {

    List<AssetDisposal> getAllDisposals();

    List<AssetDisposal> getDisposalsByApprover(User user);

    AssetDisposal getByAssetId(Long assetId);

    AssetDisposal createDisposal(AssetDisposal assetDisposal);

    AssetDisposal updateDisposal(Long id, AssetDisposal updatedDisposal);

    void deleteDisposal(Long id);

    // Missing abstract methods causing compilation errors
    AssetDisposal approveDisposal(Long disposalId, Long userId);

    AssetDisposal requestDisposal(Long assetId, AssetDisposal disposal);
}
