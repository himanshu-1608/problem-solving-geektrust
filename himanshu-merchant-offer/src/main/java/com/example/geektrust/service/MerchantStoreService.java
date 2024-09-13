package com.example.geektrust.service;

import com.example.geektrust.domain_models.Merchant;
import com.example.geektrust.domain_models.MerchantStore;

public class MerchantStoreService {

    private static MerchantStoreService merchantStoreService;

    private final MerchantService merchantService = MerchantService.getInstance();

    public static MerchantStoreService getInstance() {
        if (merchantStoreService == null) {
            merchantStoreService = new MerchantStoreService();
        }
        return merchantStoreService;
    }

    public MerchantStore createStoreForMerchant(String merchantId, String storeId) throws Exception {
        Merchant merchant = merchantService.getMerchantById(merchantId);
        return merchant.createStore(storeId);
    }

}
