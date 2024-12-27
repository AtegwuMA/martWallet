package com.example.martwallet.repository;


import com.example.martwallet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);


}


//
//import com.example.martwallet.model.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//public interface UserRepository extends JpaRepository<User, Long> {
//    User findByUsername(String username);
//    User findByEmail(String email);
//}
