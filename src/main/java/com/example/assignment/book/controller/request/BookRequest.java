package com.example.assignment.book.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.ISBN;

@Schema(description = "도서 위탁 요청 객체")
public record BookRequest(


        @Schema(description = "도서 명", example = "컬처, 문화로 쓴 세계사")
        @NotBlank(message = "도서 명은 필수입니다.")
        String title,

        @Schema(description = "ISBN", example = "9791167741370")
        @ISBN(type = ISBN.Type.ANY, message = "ISBN 형식이 아닙니다.")
        @NotBlank(message = "ISBN는 필수입니다.")
        String isbn,

        @Schema(description = "대여 가격", example = "1500")
        @Positive(message = "대여 금액은 0보다 커야합니다.")
        @NotNull(message = "대여 금액은 필수입니다.")
        Integer rentalPrice
) {
}
