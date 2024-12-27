package com.example.martwallet.service;

import com.example.martwallet.dto.requestdto.AddWalletRequest;
import com.example.martwallet.dto.requestdto.TransactionAmount;
import com.example.martwallet.dto.responsedto.GenericResponse;
import com.example.martwallet.model.Wallet;
import org.springframework.data.domain.Pageable;

public interface WalletService {
    GenericResponse fundWallet(Long userId, Long walletId, TransactionAmount amount);

    GenericResponse transferFunds(Long fromUserId, Long fromWalletId, Long toUserId, Long toWalletId, TransactionAmount amount);

    GenericResponse withdraw(Long userId, Long walletId, TransactionAmount amount);

    GenericResponse getWalletBalance(Long userId, Long walletId);

    GenericResponse getAllTransactionHistories(Long userId, Pageable pageable);

    GenericResponse addWallet(Long userId, AddWalletRequest newWallet);
}
