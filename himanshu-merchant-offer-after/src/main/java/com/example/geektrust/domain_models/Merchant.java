package com.example.geektrust.domain_models;

import com.example.geektrust.enums.Location;
import com.example.geektrust.enums.MerchantType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Merchant {

    private final String id;

    private final MerchantType type;

    private final List<MerchantStore> stores = new ArrayList<>();

    private final int storeLimit;

    private final Date activationDate = new Date();

    private final Location location;

    public Merchant(String id, MerchantType type, Location location) throws Exception {
        this.id = id;
        this.type = type;
        this.storeLimit = getMerchantTypeWiseStoreLimit(type);
        this.location = location;
    }

    private int getMerchantTypeWiseStoreLimit(MerchantType merchantType) throws Exception {
        // returning -1 for no store limit
        switch (merchantType) {
            case P2P: return 1;
            case P2ML:
            case P2M:
                return -1;
            default: throw new Exception("Merchant Type not handled: " + merchantType.name());
        }
    }

    public String getId() {
        return id;
    }

    public MerchantStore createStore(String storeId) throws Exception {
        if(stores.size() == storeLimit) {
            throw new Exception("Store limit reached for merchant: " + id);
        }
        MerchantStore merchantStore = new MerchantStore(storeId);
        stores.add(merchantStore);
        return merchantStore;
    }

    public MerchantType getType() {
        return type;
    }

    public MerchantStore getStoreById(String storeId) {
        return stores.stream().filter(store -> store.getId().equals(storeId)).findFirst().orElse(null);
    }
}
