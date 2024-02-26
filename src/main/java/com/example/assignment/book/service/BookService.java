package com.example.assignment.book.service;

import com.example.assignment.account.entity.Account;
import com.example.assignment.book.controller.request.BookRequest;
import com.example.assignment.book.controller.request.BookSorting;
import com.example.assignment.book.controller.response.BookResponse;
import com.example.assignment.book.entity.Book;
import com.example.assignment.book.enums.RentalStatusType;
import com.example.assignment.book.repository.BookRepository;
import com.example.assignment.global.controller.request.Paging;
import com.example.assignment.global.controller.response.PageResponse;
import com.example.assignment.global.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    @Transactional
    public void consignment(BookRequest request, Account account) {

        Book book = new Book(request, account);

        repository.save(book);
    }

    @Transactional(readOnly = true)
    public PageResponse<BookResponse> findAll(Paging paging, BookSorting sorting) {

        List<BookResponse> data = null;

        Page<Book> all = repository.findAllByRentalStatus(RentalStatusType.STORE, paging.parse().withSort(sorting.parser()));

        if (all.hasContent()) {
            data = all.getContent().stream().map(BookResponse::new).toList();
        }

        return new PageResponse<>(all.getTotalElements(), data);
    }

    @Transactional(readOnly = true)
    public List<Book> findStoreTarget(LocalDateTime referenceDate) {

        return repository.findStoreTargetList(referenceDate);
    }

    @Transactional
    public void rental(List<Long> idList, Account account) {

        int rental = repository.rental(idList, account.getId(), LocalDateTime.now());

        if (rental != idList.size()) {
            throw new BadRequestException("일부 도서를 다른 사람이 대출을 진행했습니다. 확인 후 다시 시도해주세요.");
        }
    }
}
