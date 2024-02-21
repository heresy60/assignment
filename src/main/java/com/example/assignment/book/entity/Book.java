package com.example.assignment.book.entity;

import com.example.assignment.book.controller.request.BookRequest;
import com.example.assignment.book.enums.RentalStatusType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private RentalStatusType rentalStatus = RentalStatusType.STORE;

    public Book(BookRequest request) {
        this.title = request.title();
        this.isbn = request.isbn();
        this.rentalPrice = request.rentalPrice();
    }
}
