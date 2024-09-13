package com.example.geektrust.domain_models;

public class MerchantStore {

    private final String id;

    private int transactionCount = 0;

    public MerchantStore(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void doTransaction () {
        transactionCount++;
    }
}
