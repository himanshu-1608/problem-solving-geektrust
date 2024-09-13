package com.example.geektrust;

import com.example.geektrust.service.MerchantService;
import com.example.geektrust.service.MerchantStoreService;
import com.example.geektrust.service.OfferRuleService;
import com.example.geektrust.service.OfferService;

public class ApplicationContext {

    // Singleton, just like @Component/@Service
    private final MerchantService merchantService = MerchantService.getInstance();

    private final MerchantStoreService merchantStoreService = MerchantStoreService.getInstance();

    private final OfferService offerService = OfferService.getInstance();

    private final OfferRuleService offerRuleService = OfferRuleService.getInstance();

    public MerchantService getMerchantService() {
        return merchantService;
    }

    public MerchantStoreService getMerchantStoreService() {
        return merchantStoreService;
    }

    public OfferService getOfferService() {
        return offerService;
    }

    public OfferRuleService getOfferRuleService() {
        return offerRuleService;
    }
}
