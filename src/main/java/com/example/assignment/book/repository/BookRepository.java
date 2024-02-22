package com.example.assignment.book.repository;

import com.example.assignment.book.entity.Book;
import com.example.assignment.book.enums.RentalStatusType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findAllByRentalStatus(RentalStatusType rentalStatusType, Pageable pageable);
}
