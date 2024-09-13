package com.example.geektrust.domain_models.rule;

import com.example.geektrust.domain_models.Merchant;
import com.example.geektrust.domain_models.MerchantStore;
import com.example.geektrust.domain_models.Offer;

public interface IOfferRule {

    boolean isRuleApplicable(Merchant merchant, MerchantStore merchantStore, Offer offer, Object... objects);

}
