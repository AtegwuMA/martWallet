package com.example.martwallet.model;

import com.example.martwallet.enums.WalletTier;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String password;
    private String dateOfBirth;
    private String address;
    private String bvn;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Wallet> wallets;
}
