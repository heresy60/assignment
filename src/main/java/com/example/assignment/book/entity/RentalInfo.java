package com.example.assignment.book.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Embeddable
public class RentalInfo {

    private int rentalCount = 0;
    private LocalDateTime lastRentalDate;

    public void renew() {
        this.rentalCount++;
        this.lastRentalDate = LocalDateTime.now();
    }
}
