package com.example.assignment.book.controller;

import com.example.assignment.book.controller.request.BookRequest;
import com.example.assignment.book.entity.Book;
import com.example.assignment.book.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("도서 관련 테스트")
class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    BookRepository repository;

    private final String root = "/api/books";

    @BeforeEach
    void init() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("도서 위탁 등록")
    void save() throws Exception {

        BookRequest bookRequest = new BookRequest("자바 퍼시스턴스 프로그래밍 완벽 가이드", "9791158394769", 1500);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post(root)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookRequest)));

        result.andExpect(status().isCreated());
    }

    @Test
    @DisplayName("위탁 도서 등록 정보가 잘못된 경우")
    void saveFail() throws Exception {

        BookRequest noTitle = new BookRequest("", "9791158394769", 1500);

        ResultActions noTitleResult = mockMvc.perform(MockMvcRequestBuilders.post(root)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(noTitle)));

        noTitleResult.andExpect(status().isBadRequest());
        noTitleResult.andExpect(jsonPath("$.description").value("요청 값을 검증 중 오류가 발생했습니다."));
        noTitleResult.andExpect(jsonPath("$.messages[0].message").value("도서 명은 필수입니다."));
        noTitleResult.andExpect(jsonPath("$.messages[0].field").value("title"));


        BookRequest wrongISBN = new BookRequest("자바 퍼시스턴스 프로그래밍 완벽 가이드", "9791158334769", 1500);

        ResultActions wrongISBNResult = mockMvc.perform(MockMvcRequestBuilders.post(root)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(wrongISBN)));

        wrongISBNResult.andExpect(status().isBadRequest());
        wrongISBNResult.andExpect(jsonPath("$.description").value("요청 값을 검증 중 오류가 발생했습니다."));
        wrongISBNResult.andExpect(jsonPath("$.messages[0].message").value("ISBN 형식이 아닙니다."));
        wrongISBNResult.andExpect(jsonPath("$.messages[0].field").value("isbn"));

        BookRequest noISBN = new BookRequest("자바 퍼시스턴스 프로그래밍 완벽 가이드", null, 1500);

        ResultActions noISBNResult = mockMvc.perform(MockMvcRequestBuilders.post(root)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(noISBN)));

        noISBNResult.andExpect(status().isBadRequest());
        noISBNResult.andExpect(jsonPath("$.description").value("요청 값을 검증 중 오류가 발생했습니다."));
        noISBNResult.andExpect(jsonPath("$.messages[0].message").value("ISBN는 필수입니다."));
        noISBNResult.andExpect(jsonPath("$.messages[0].field").value("isbn"));

        BookRequest noPositive = new BookRequest("자바 퍼시스턴스 프로그래밍 완벽 가이드", "9791158394769", 0);

        ResultActions noPositiveResult = mockMvc.perform(MockMvcRequestBuilders.post(root)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(noPositive)));

        noPositiveResult.andExpect(status().isBadRequest());
        noPositiveResult.andExpect(jsonPath("$.description").value("요청 값을 검증 중 오류가 발생했습니다."));
        noPositiveResult.andExpect(jsonPath("$.messages[0].message").value("대여 금액은 0보다 커야합니다."));
        noPositiveResult.andExpect(jsonPath("$.messages[0].field").value("rentalPrice"));

        BookRequest noRentalPrice = new BookRequest("자바 퍼시스턴스 프로그래밍 완벽 가이드", "9791158394769", null);

        ResultActions noRentalPriceResult = mockMvc.perform(MockMvcRequestBuilders.post(root)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(noRentalPrice)));

        noRentalPriceResult.andExpect(status().isBadRequest());
        noRentalPriceResult.andExpect(jsonPath("$.description").value("요청 값을 검증 중 오류가 발생했습니다."));
        noRentalPriceResult.andExpect(jsonPath("$.messages[0].message").value("대여 금액은 필수입니다."));
        noRentalPriceResult.andExpect(jsonPath("$.messages[0].field").value("rentalPrice"));

    }

    @Test
    @DisplayName("대여 가능한 도서 목록 조회")
    void findAll() throws Exception {

        initBookData();

        ResultActions resultOrderPrice = mockMvc.perform(MockMvcRequestBuilders.get(root).param("property", "price")
                .param("direction", "ASC"));

        resultOrderPrice.andExpect(status().isOk());
        resultOrderPrice.andExpect(jsonPath("$.totalCount").value(49));
        resultOrderPrice.andExpect(jsonPath("$.data[0].title").value("도서 명1"));

        ResultActions resultOrderCreateDate = mockMvc.perform(MockMvcRequestBuilders.get(root).param("property", "createDate")
                .param("direction", "DESC"));

        resultOrderCreateDate.andExpect(status().isOk());
        resultOrderCreateDate.andExpect(jsonPath("$.totalCount").value(49));
        resultOrderCreateDate.andExpect(jsonPath("$.data[0].title").value("도서 명49"));
    }

    void initBookData() {

        for (int i = 1; i < 50; i++) {
            repository.save(new Book(new BookRequest("도서 명" + i, "9791161758213", 1600 + i)));
        }
    }
}