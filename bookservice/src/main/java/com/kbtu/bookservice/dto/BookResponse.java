package com.kbtu.bookservice.dto;

import com.kbtu.bookservice.entity.Book;

public class BookResponse {

    private Long id;
    private String title;
    private String author;
    private int year;
    private String status;

    // Конструктор из Entity
    public BookResponse(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.year = book.getYear();
        this.status = book.getStatus().name();
    }

    // Геттеры
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public String getStatus() {
        return status;
    }
}
