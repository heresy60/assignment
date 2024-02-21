package com.example.assignment.book.controller;

import com.example.assignment.book.controller.request.BookRequest;
import com.example.assignment.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;

    @PostMapping
    public ResponseEntity<Void> consignment(@Validated @RequestBody BookRequest request) {
        service.consignment(request);

        return ResponseEntity.status(201).build();
    }
}
