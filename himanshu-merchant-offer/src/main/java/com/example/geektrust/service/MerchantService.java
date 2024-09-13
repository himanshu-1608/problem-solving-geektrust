package com.example.geektrust.service;

import com.example.geektrust.domain_models.Merchant;
import com.example.geektrust.enums.Location;
import com.example.geektrust.enums.MerchantType;

import java.util.ArrayList;
import java.util.List;

public class MerchantService {

    private final List<Merchant> merchantList = new ArrayList<>();

    private static MerchantService merchantService;

    public static MerchantService getInstance() {
        if(merchantService == null) {
            merchantService = new MerchantService();
        }
        return merchantService;
    }

    public Merchant onboardMerchant(String merchantId, MerchantType merchantType, Location state) throws Exception {
        Merchant merchant = new Merchant(merchantId, merchantType, state);
        merchantList.add(merchant);
        return merchant;
    }

    public Merchant getMerchantById(String merchantId) throws Exception {
        return merchantList.stream()
                .filter(merchant -> merchant.getId().equals(merchantId))
                .findFirst()
                .orElseThrow(() -> new Exception("Merchant not found"));
    }
}
