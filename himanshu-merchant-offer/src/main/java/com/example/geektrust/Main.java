package com.example.geektrust;

import com.example.geektrust.domain_models.Merchant;
import com.example.geektrust.domain_models.Offer;
import com.example.geektrust.domain_models.OfferRule;
import com.example.geektrust.enums.Location;
import com.example.geektrust.enums.MerchantType;
import com.example.geektrust.service.MerchantService;
import com.example.geektrust.service.MerchantStoreService;
import com.example.geektrust.service.OfferRuleService;
import com.example.geektrust.service.OfferService;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws Exception {

        ApplicationContext applicationContext = new ApplicationContext();

        MerchantService merchantService = applicationContext.getMerchantService();
        MerchantStoreService merchantStoreService = applicationContext.getMerchantStoreService();
        OfferService offerService = applicationContext.getOfferService();
        OfferRuleService offerRuleService = applicationContext.getOfferRuleService();

        // Functionality 1: Onboard a merchant
        Merchant merchantP2P = merchantService.onboardMerchant("merchantp2p", MerchantType.P2P, Location.TAMIL_NADU);
        Merchant merchantP2ML = merchantService.onboardMerchant("merchantp2ml", MerchantType.P2ML, Location.KARNATAKA);
        Merchant merchantP2M = merchantService.onboardMerchant("merchantp2m", MerchantType.P2M, Location.HARYANA);

        // Functionality 2: Create a store for a merchant
        merchantStoreService.createStoreForMerchant(merchantP2P.getId(), "merchantp2pStore1");
        // It's validation will be added in test cases
        try {
            merchantStoreService.createStoreForMerchant(merchantP2P.getId(), "merchantp2pStore1");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        // Functionality 3: Create offer
        Offer offer1 = offerService.createOffer("OFFER1", "Tamil Nadu merchants between June-July");
        Offer offer2 = offerService.createOffer("OFFER2", "Non-Tamil Nadu merchants between July-August");
        Offer offer3 = offerService.createOffer("OFFER3", "5+ transactions or 500+ amount");
        Offer offer4 = offerService.createOffer("OFFER4", "15+ transactions or 1500+ amount");

        // Functionality 4: Create Offer Rules
        OfferRule rule1 = offerRuleService.createOfferRule("Rule1", offer1,
                Arrays.asList(MerchantType.P2P, MerchantType.P2ML, MerchantType.P2M));
        rule1.addWhitelistedLocation(Location.TAMIL_NADU);
        rule1.setStartMonth(6);
        rule1.setEndMonth(7);

        OfferRule rule2 = offerRuleService.createOfferRule("Rule2", offer2,
                Arrays.asList(MerchantType.P2P, MerchantType.P2ML, MerchantType.P2M));
        rule2.addBlacklistedLocation(Location.TAMIL_NADU);
        rule2.setStartMonth(7);
        rule2.setEndMonth(8);

        OfferRule rule3 = offerRuleService.createOfferRule("Rule3", offer3, Collections.singletonList(MerchantType.P2ML));
        rule3.setMinTransactions(5);
        rule3.setMinAmount(500);

        OfferRule rule4 = offerRuleService.createOfferRule("Rule4", offer4, Collections.singletonList(MerchantType.P2M));
        rule4.setMinTransactions(5);
        rule4.setMinAmount(500);

        // Functionality 5: Get Offer for a merchant
        offerService.getOfferForMerchantAndStore(merchantP2P.getId(), "merchantp2pStore1");
    }
}
