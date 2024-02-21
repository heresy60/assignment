package com.example.assignment.book.controller;

import com.example.assignment.book.controller.request.BookRequest;
import com.example.assignment.book.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
