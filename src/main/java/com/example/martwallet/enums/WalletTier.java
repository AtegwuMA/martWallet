package com.example.martwallet.enums;

import lombok.Data;
import lombok.Getter;

@Getter
public enum WalletTier {
    BASIC(1000, 5000),  // daily limit, weekly limit
    SILVER(5000, 25000),
    GOLD(10000, 50000);

    private final double dailyLimit;
    private final double weeklyLimit;

    WalletTier(double dailyLimit, double weeklyLimit) {
        this.dailyLimit = dailyLimit;
        this.weeklyLimit = weeklyLimit;
    }

}
