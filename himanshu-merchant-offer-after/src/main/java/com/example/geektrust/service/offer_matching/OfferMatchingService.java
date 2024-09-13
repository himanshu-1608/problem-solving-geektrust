package com.example.geektrust.service.offer_matching;

import com.example.geektrust.domain_models.Merchant;
import com.example.geektrust.domain_models.MerchantStore;
import com.example.geektrust.domain_models.Offer;

public interface OfferMatchingService {

    Offer getOffer(Merchant merchant, MerchantStore merchantStore);

}
