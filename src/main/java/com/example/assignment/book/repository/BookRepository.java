package com.example.assignment.book.repository;

import com.example.assignment.book.entity.Book;
import com.example.assignment.book.enums.RentalStatusType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findAllByRentalStatus(RentalStatusType rentalStatusType, Pageable pageable);

    @Query("select book from Book book where book.rentalInfo.lastRentalDate <= :referenceDate and book.rentalStatus = 'RENTAL'")
    List<Book> findStoreTargetList(LocalDateTime referenceDate);
}
