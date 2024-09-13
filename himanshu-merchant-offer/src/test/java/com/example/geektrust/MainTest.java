package com.example.geektrust;

import com.example.geektrust.domain_models.Merchant;
import com.example.geektrust.enums.Location;
import com.example.geektrust.enums.MerchantType;
import com.example.geektrust.service.MerchantService;
import com.example.geektrust.service.MerchantStoreService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    @DisplayName("Multiple Stores Not Allowed for P2P merchants")
    public void testMultipleStoresNotAllowedP2P() throws Exception {
        MerchantService merchantService = new MerchantService();
        MerchantStoreService merchantStoreService = new MerchantStoreService();
        Merchant merchant = merchantService.onboardMerchant("m1", MerchantType.P2P, Location.HARYANA);

        merchantStoreService.createStoreForMerchant(merchant.getId(), "s1");

        Assertions.assertThrows(Exception.class, () -> {
            merchantStoreService.createStoreForMerchant(merchant.getId(), "s2");
        }, "Store limit reached for merchant: "+merchant.getId());
    }

}