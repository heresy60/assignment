package com.example.assignment.book.controller;

import com.example.assignment.book.controller.request.BookRequest;
import com.example.assignment.book.controller.request.BookSorting;
import com.example.assignment.book.controller.response.BookResponse;
import com.example.assignment.book.service.BookService;
import com.example.assignment.global.controller.request.Paging;
import com.example.assignment.global.controller.response.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "도서 관련")
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;

    @PostMapping
    @Operation(summary = "도서 위탁", description = "위탁 도서 정보를 등록하는 API 입니다.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "정상적으로 위탁 도서정보가 등록되었습니다.")
            })
    public ResponseEntity<Void> consignment(@Validated @RequestBody BookRequest request) {
        service.consignment(request);

        return ResponseEntity.status(201).build();
    }

    @GetMapping
    @Operation(summary = "대여 가능한 도서 목록 조회", description = "대여할 수 있는 도서 정보 목록을 조회하는 API 입니다.",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "page", description = "페이지 번호"),
                    @Parameter(in = ParameterIn.QUERY, name = "size", description = "조회 개수"),
                    @Parameter(in = ParameterIn.QUERY, name = "column", description = "정렬 필드", example = "createDate = 등록일, price = 대여 금액, rentalCount = 대여 수"),
                    @Parameter(in = ParameterIn.QUERY, name = "direction", description = "정렬 필드", example = "DESC | ASC"),
            },
            responses = {
                    @ApiResponse(responseCode = "201", description = "정상적으로 위탁 도서정보가 등록되었습니다.")
            })
    public ResponseEntity<PageResponse<BookResponse>> findAll(@Validated Paging paging, @Validated BookSorting bookSorting) {

        return ResponseEntity.ok(service.findAll(paging, bookSorting));
    }
}
