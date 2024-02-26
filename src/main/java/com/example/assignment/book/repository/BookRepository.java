package com.example.assignment.book.repository;

import com.example.assignment.book.entity.Book;
import com.example.assignment.book.enums.RentalStatusType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findAllByRentalStatus(RentalStatusType rentalStatusType, Pageable pageable);

    @Query("select book from Book book where book.rentalInfo.lastRentalDate <= :referenceDate and book.rentalStatus = 'RENTAL'")
    List<Book> findStoreTargetList(LocalDateTime referenceDate);

    @Modifying(clearAutomatically = true)
    @Query("update Book book set book.rentalStatus = 'RENTAL', book.rentalInfo.rentalCount = book.rentalInfo.rentalCount+1,book.rentalInfo.lastRenter.id =?2, book.rentalInfo.lastRentalDate = ?3  where book.rentalStatus = 'STORE' and book.id in ?1")
    int rental(List<Long> id, Long accountId, LocalDateTime rentalDate);
}
