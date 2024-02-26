package com.example.assignment.book.controller.response;

import com.example.assignment.book.entity.Book;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "위탁 도서 목록 응답 객체")
public record BookResponse(

        @Schema(description = "고유 값", example = "1")
        long id,

        @Schema(description = "위탁 도서 명", example = "핵심 데이터 모델링")
        String title,

        @Schema(description = "ISBN", example = "9791196395766")
        String isbn,

        @Schema(description = "대여 금액", example = "1000")
        int rentalPrice,

        Consignor consignor

) {
    public BookResponse(Book book) {
        this(book.getId(), book.getTitle(), book.getIsbn(), book.getRentalPrice(), new Consignor(book.getConsignor().getUsername()));
    }
}

@Schema(description = "위탁자 정보")
record Consignor(

        @Schema(description = "이름", example = "박근호")
        String name
){}
