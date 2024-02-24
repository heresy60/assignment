package com.example.assignment.account.serivce;

import com.example.assignment.account.controller.request.SignupRequest;
import com.example.assignment.account.entity.Account;
import com.example.assignment.account.repository.AccountRepository;
import com.example.assignment.global.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignupService {

    private final AccountRepository repository;

    @Transactional
    public void signup(SignupRequest request) {

        if (repository.existsByEmail(request.email())) {
            throw new BadRequestException("이미 가입된 이메일 주소입니다.");
        }

        if (repository.existsByPhoneNumber(request.phoneNumber())) {
            throw new BadRequestException("이미 가입된 핸드폰 번호입니다.");
        }

        try {
            Account account = new Account(request);
            repository.save(account);
        } catch (DataIntegrityViolationException e) {

            if (e.getMessage().contains("UK_EMAIL")) {

                throw new BadRequestException("이미 가입된 이메일 주소입니다.");
            }  else if (e.getMessage().contains("UK_PHONE_NUMBER")) {

                throw new BadRequestException("이미 가입된 핸드폰 번호입니다.");
            } else {
                throw new RuntimeException();
            }
        }
    }
}
