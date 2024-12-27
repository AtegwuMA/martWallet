package com.example.martwallet.repository;

import com.example.martwallet.model.WalletTier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletTierRepository extends JpaRepository<WalletTier, Long> {
    WalletTier findByName(String name);
}
