package com.example.geektrust.service;

import com.example.geektrust.domain_models.Merchant;
import com.example.geektrust.domain_models.Offer;
import com.example.geektrust.service.offer_matching.OfferMatchingService;
import com.example.geektrust.service.offer_matching.OfferMatchingServiceFactory;

import java.util.ArrayList;
import java.util.List;

public class OfferService {

    private final MerchantService merchantService = MerchantService.getInstance();

    List<Offer> offerList = new ArrayList<>();

    private static OfferService offerService;

    public static OfferService getInstance() {
        if(offerService == null) {
            offerService = new OfferService();
        }
        return offerService;
    }

    public Offer createOffer(String offerId, String offerDescription) {
        Offer offer = new Offer(offerId, offerDescription);
        offerList.add(offer);
        return offer;
    }

    public Offer getOfferForMerchantAndStore(String merchantId, String storeId) throws Exception {
        Merchant merchant = merchantService.getMerchantById(merchantId);
        OfferMatchingService offerMatchingService = OfferMatchingServiceFactory.getOfferMatchingService(merchant.getType());
        return offerMatchingService.getOffer(merchant, merchant.getStoreById(storeId));
    }

}
