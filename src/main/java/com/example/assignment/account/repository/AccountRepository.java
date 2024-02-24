package com.example.assignment.account.repository;

import com.example.assignment.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);
}
