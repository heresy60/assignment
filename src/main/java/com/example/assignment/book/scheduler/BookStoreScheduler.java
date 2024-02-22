package com.example.assignment.book.scheduler;

import com.example.assignment.book.entity.Book;
import com.example.assignment.book.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookStoreScheduler {

    private final BookService bookService;

    @Transactional
    @Scheduled(fixedDelay = 1000)
    public void store() {

        List<Book> storeTarget = bookService.findStoreTarget(LocalDateTime.now().minusMinutes(20));

        for (Book book : storeTarget) {

            try {
                book.store();
            } catch (Exception e) {
                log.error("${} 위탁 도서 반납 처리 중 에러 발생", book.getId());
            }
        }
    }
}
