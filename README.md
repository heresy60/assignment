# 채용 과제

<br />

1. [프로젝트 소개 ](#1-프로젝트-소개)
2. [기술 스택](#2-기술-스택-)
3. [구현 기능](#3-구현-기능)
4. [추후 개선 사항](#4-추후-개선-사항)
5. [프로젝트 실행 방법](#5-프로젝트-실행-방법)

<br/>

<br />

## 1. 프로젝트 소개

- 스웨거 링크는 http://localhost:8080/swagger-ui/index.html#/ 입니다.

<br />

## 2. 기술 스택 
- Spring boot 
- Spring data jpa
- H2
- JWT
- Swagger
- Mariadb

<br />

## 3. 구현 기능
- [x] 회원가입
- [x] 로그인
- [x] 도서 위탁
- [x] 대여 가능 도서 목록 조회
- [x] 대여
- [x] 반납


## 4. 추후 개선 사항
1. 대여의 경우 동시성 처리 관련 문제가 발생할 수 있습니다. 동시성 관련해서 구현 할 수 있으나 논의해야할 사항이 발생하므로 단순하게 처리하였으며 만약 구현해야하면 아래 방식으로 처리 가능합니다.
   1. MQ 활용해서 처리 진행
   2. Table Lock 활용 처리
   3. Redis 활용처리
2. 반납의 경우 스케쥴을 활용하여 20초 지난 대여책을 반납처리하고 있으나 실 사용에서는 사용자 행위 방식으로 변경해야 합니다.
3. 구현 요구사항에 로그인은 없었으나 위탁이나 대여의 경우 회원 기능으로 보여 로그인을 구현하였으나 임의로 이메일, 비밀번호로 로그인 방식을 구현한 상태이며 추후 요구사항에 따라 변경해야 할 것으로 보입니다.

<br />

## 5. 프로젝트 실행 방법
1. 프로젝트를 실행하는데 docker가 필요합니다.
2. ./gradlew clean build 명령어로 프로젝트 빌드 진행
3. docker-compose up 명령어로 프로젝트 Image 및 프로젝트 실행
