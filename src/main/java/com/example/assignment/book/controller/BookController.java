package com.example.assignment.book.controller;

import com.example.assignment.account.entity.Account;
import com.example.assignment.book.controller.request.BookRentalRequest;
import com.example.assignment.book.controller.request.BookRequest;
import com.example.assignment.book.controller.request.BookSorting;
import com.example.assignment.book.controller.response.BookResponse;
import com.example.assignment.book.service.BookService;
import com.example.assignment.global.controller.request.Paging;
import com.example.assignment.global.controller.response.PageResponse;
import com.example.assignment.global.exception.response.BaseErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "도서 관련")
@RequestMapping("/api/consignment/books")
public class BookController {

    private final BookService service;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "도서 위탁", description = "위탁 도서 정보를 등록하는 API 입니다.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "정상적으로 위탁 도서정보가 등록되었습니다.")
            })
    public ResponseEntity<Void> consignment(@Validated @RequestBody BookRequest request, @AuthenticationPrincipal Account account) {
        service.consignment(request, account);

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

    @PostMapping("/rental")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "도서 대여", description = "도서 대여를 진행하는 API 입니다.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "정상적으로 대여가 진행되었습니다."),
                    @ApiResponse(responseCode = "400", description = "대여 신청한 도서 중 일부 도서를 다른 사람이 대여 했습니다.", content = @Content(schema = @Schema(implementation = BaseErrorResponse.class))),
            })
    public ResponseEntity<Object> rental(@RequestBody BookRentalRequest request
            , @AuthenticationPrincipal Account account) {
        service.rental(request.idList(), account);

        return ResponseEntity.noContent().build();
    }
}
