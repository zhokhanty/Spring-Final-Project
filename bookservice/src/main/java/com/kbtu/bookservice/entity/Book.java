package com.kbtu.bookservice.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(name = "publish_year", nullable = false)
    private Integer publishYear;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public enum Status {
        AVAILABLE,
        BORROWED
    }

    // FACTORY
    public static Book create(String title, String author, Integer publishYear) {
        Book book = new Book();
        book.title = title;
        book.author = author;
        book.publishYear = publishYear;
        book.status = Status.AVAILABLE;
        return book;
    }

    // DOMAIN METHODS
    public void updateInfo(String title, String author, Integer publishYear) {
        this.title = title;
        this.author = author;
        this.publishYear = publishYear;
    }

    public void markBorrowed() {
        this.status = Status.BORROWED;
    }

    public void markReturned() {
        this.status = Status.AVAILABLE;
    }
}
