package com.example.assignment.book.entity;

import com.example.assignment.account.entity.Account;
import com.example.assignment.book.controller.request.BookRequest;
import com.example.assignment.book.enums.RentalStatusType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
        @Index(name = "idx_rental_price", columnList = "rentalPrice"),
        @Index(name = "idx_rental_count", columnList = "rentalCount"),
})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * 제목
     */
    private String title;

    /**
     * ISBN 번호
     */
    private String isbn;

    /**
     * 대여 금액
     */
    private int rentalPrice;

    /**
     * 대여 상태
     */
    @Enumerated(EnumType.STRING)
    private RentalStatusType rentalStatus = RentalStatusType.STORE;

    @Embedded
    private RentalInfo rentalInfo;

    @ManyToOne
    @JoinColumn(name = "consignorId")
    private Account consignor;

    public Book(BookRequest request, Account account) {
        this.title = request.title();
        this.isbn = request.isbn();
        this.rentalPrice = request.rentalPrice();
        this.rentalInfo = new RentalInfo();
        this.consignor = account;
    }

    public void store() {
        this.rentalStatus = RentalStatusType.STORE;
    }
}
