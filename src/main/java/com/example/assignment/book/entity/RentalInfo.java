package com.example.assignment.book.entity;

import com.example.assignment.account.entity.Account;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Embeddable
public class RentalInfo {

    private int rentalCount = 0;
    private LocalDateTime lastRentalDate;

    @ManyToOne
    @JoinColumn(name = "lastRenterId")
    private Account lastRenter;
}
