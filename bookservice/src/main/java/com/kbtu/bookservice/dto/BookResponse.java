package com.kbtu.bookservice.dto;

import com.kbtu.bookservice.entity.Book;
import lombok.Getter;

@Getter
public class BookResponse {

    private final Long id;
    private final String title;
    private final String author;
    private final Integer publishYear;
    private final String status;

    public BookResponse(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.publishYear = book.getPublishYear();
        this.status = book.getStatus().name();
    }
}
