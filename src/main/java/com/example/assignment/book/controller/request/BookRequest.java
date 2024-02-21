package com.example.assignment.book.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.ISBN;

public record BookRequest(


        @NotBlank(message = "도서 명은 필수입니다.")
        String title,

        @ISBN(type = ISBN.Type.ANY, message = "ISBN 형식이 아닙니다.")
        @NotBlank(message = "ISBN는 필수입니다.")
        String isbn,

        @Positive(message = "대여 금액은 0보다 커야합니다.")
        @NotNull(message = "대여 금액은 필수입니다.")
        Integer rentalPrice
) {
}
