package com.example.assignment.account.entity;

import com.example.assignment.account.controller.request.SignupRequest;
import com.example.assignment.account.entity.converter.PasswordConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_EMAIL", columnNames = {"email"}),
                @UniqueConstraint(name = "UK_PHONE_NUMBER", columnNames = {"phoneNumber"}),
        }
)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    @Convert(converter = PasswordConverter.class)
    private String password;

    private String username;

    private String phoneNumber;

    public Account(SignupRequest request) {
        this.username = request.username();
        this.email = request.email();
        this.phoneNumber = request.phoneNumber();
        this.password = request.password();
    }
}
