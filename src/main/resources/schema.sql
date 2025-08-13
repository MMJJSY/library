-- 멤버 테이블
CREATE TABLE member (
    member_id bigint primary key auto_increment,
    email varchar(255) unique not null,
    password varchar(255) not null,
    name varchar(255) not null,
    phone varchar(255) not null,
    address varchar(255) not null,
    member_type enum ('STUDENT', 'TEACHER', 'GENERAL'),
    created_at timestamp,
    updated_at timestamp
    );

-- 책 테이블
CREATE TABLE book (
    book_id bigint primary key auto_increment,
    isbn varchar(255) unique not null,
    title varchar(255) not null,
    author varchar(255) not null,
    publisher varchar(255) not null,
    publication_year int,
    category varchar(255),
    total_quantity int,
    available_quantity int,
    location varchar(255),
    created_at timestamp
    );

-- 대출 테이블
CREATE TABLE loan (
    loan_id bigint primary key auto_increment,
    member_id bigint,
    book_id bigint,
    loan_date date,
    due_date date,
    return_date date,
    status enum ('ACTIVE','RETURNED','OVERDUE'),
    fine_amount decimal(10,2),
    created_at timestamp,
    foreign key(member_id) references member(member_id),
    foreign key(book_id) references book(book_id)
    );

-- 예약 테이블
CREATE TABLE reservation (
    reservation_id bigint primary key auto_increment,
    member_id bigint,
    book_id bigint,
    reservation_date date,
    expiry_date date,
    status enum ('ACTIVE','CANCELLED','FULFILLED'),
    queue_position int,
    created_at timestamp,
    updated_at timestamp,
    foreign key(member_id) references member(member_id),
    foreign key(book_id) references book(book_id)
    );
