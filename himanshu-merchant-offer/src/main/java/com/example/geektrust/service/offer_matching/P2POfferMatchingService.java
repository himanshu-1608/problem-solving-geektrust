package com.example.geektrust.service.offer_matching;

import com.example.geektrust.domain_models.Merchant;
import com.example.geektrust.domain_models.MerchantStore;
import com.example.geektrust.domain_models.Offer;
import com.example.geektrust.service.OfferRuleService;
import com.example.geektrust.service.OfferService;

import java.util.List;
import java.util.Set;

public class P2POfferMatchingService implements OfferMatchingService {

    OfferRuleService offerRuleService = OfferRuleService.getInstance();

    @Override
    public Offer getOffer(Merchant merchant, MerchantStore merchantStore) {
        Set<Offer> getMerchantBasedOffers = offerRuleService.getMerchantBasedOffers();
        // TODO: Add code for validation of offer
        return null;
    }
}
