package com.example.assignment.book.entity;

import com.example.assignment.book.controller.request.BookRequest;
import com.example.assignment.book.enums.RentalStatusType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
        @Index(name = "idx_rental_price", columnList = "rentalPrice")
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

    public Book(BookRequest request) {
        this.title = request.title();
        this.isbn = request.isbn();
        this.rentalPrice = request.rentalPrice();
    }
}
