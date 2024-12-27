package com.example.martwallet.repository;

import com.example.martwallet.model.Transaction;
import com.example.martwallet.model.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByWalletId(Long walletId);
    Page<Transaction> findByWallet(Wallet wallet, Pageable pageable);
}
