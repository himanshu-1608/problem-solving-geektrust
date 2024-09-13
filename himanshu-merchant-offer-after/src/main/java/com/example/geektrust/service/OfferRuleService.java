package com.example.geektrust.service;

import com.example.geektrust.domain_models.Offer;
import com.example.geektrust.domain_models.OfferRule;
import com.example.geektrust.enums.MerchantType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OfferRuleService {

    private final List<OfferRule> offerRuleList = new ArrayList<>();

    private static OfferRuleService offerRuleService;

    public static OfferRuleService getInstance() {
        if(offerRuleService == null) {
            offerRuleService = new OfferRuleService();
        }
        return offerRuleService;
    }

    public OfferRule createOfferRule(String ruleId, Offer offer, List<MerchantType> allowedMerchantTypes) {
        OfferRule offerRule = new OfferRule(ruleId, offer, allowedMerchantTypes);
        offerRuleList.add(offerRule);
        return offerRule;
    }

    public Set<Offer> getMerchantBasedOffers() {
        Set<Offer> merchantBasedOffers = new HashSet<>();
        for(OfferRule offerRule : offerRuleList) {
            if(offerRule.getAllowedMerchantTypes().contains(MerchantType.P2P)) {
                merchantBasedOffers.add(offerRule.getOffer());
            }
        }
        return merchantBasedOffers;
    }
}
