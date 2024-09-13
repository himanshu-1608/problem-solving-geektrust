package com.example.geektrust.domain_models;

import com.example.geektrust.enums.Location;
import com.example.geektrust.enums.MerchantType;

import java.util.ArrayList;
import java.util.List;

public class OfferRule {

    private final String id;

    private final Offer appliedOffer;

    private final List<MerchantType> allowedMerchantTypes;

    private final List<Location> whitelistedLocations = new ArrayList<>();

    private List<Location> blacklistedLocations = new ArrayList<>();

    private int startMonth;

    private int endMonth;

    private int minTransactions;

    private int minAmount;

    public OfferRule(String id, Offer appliedOffer, List<MerchantType> allowedMerchantTypes) {
        this.id = id;
        this.appliedOffer = appliedOffer;
        this.allowedMerchantTypes = allowedMerchantTypes;
    }

    public void addWhitelistedLocation(Location whitelistedLocation) {
        this.whitelistedLocations.add(whitelistedLocation);
    }

    public void addBlacklistedLocation(Location blacklistedLocation) {
        this.blacklistedLocations.add(blacklistedLocation);
    }

    public void setStartMonth(int startMonth) {
        this.startMonth = startMonth;
    }

    public void setEndMonth(int endMonth) {
        this.endMonth = endMonth;
    }

    public void setMinTransactions(int minTransactions) {
        this.minTransactions = minTransactions;
    }

    public void setMinAmount(int minAmount) {
        this.minAmount = minAmount;
    }

    public List<MerchantType> getAllowedMerchantTypes() {
        return allowedMerchantTypes;
    }

    public Offer getOffer() {
        return appliedOffer;
    }
}
