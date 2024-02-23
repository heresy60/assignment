package com.example.assignment.book.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "대여 신청 요청 객체")
public record BookRentalRequest(

        @Schema(description = "대여할 위탁 도서 고유 값 목록", example = "[1,2,3]")
        List<Long> idList
) {
}
