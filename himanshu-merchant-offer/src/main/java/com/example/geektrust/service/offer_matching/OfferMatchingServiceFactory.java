package com.example.geektrust.service.offer_matching;

import com.example.geektrust.enums.MerchantType;

public class OfferMatchingServiceFactory {

    public static OfferMatchingService getOfferMatchingService(MerchantType merchantType) {
        switch (merchantType) {
            case P2P:
                return new P2POfferMatchingService();
            case P2ML:
                return new P2MLOfferMatchingService();
            case P2M:
                return new P2MOfferMatchingService();
            default:
                return null;
        }
    }
}
