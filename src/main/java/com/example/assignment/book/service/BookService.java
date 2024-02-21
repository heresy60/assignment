package com.example.assignment.book.service;

import com.example.assignment.book.controller.request.BookRequest;
import com.example.assignment.book.entity.Book;
import com.example.assignment.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    @Transactional
    public void consignment(BookRequest request) {

        Book book = new Book(request);

        repository.save(book);
    }
}
