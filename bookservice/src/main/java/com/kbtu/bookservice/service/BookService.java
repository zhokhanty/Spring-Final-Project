package com.kbtu.bookservice.service;

import com.kbtu.bookservice.entity.Book;

import java.util.List;

public interface BookService {

    Book create(Book book);

    Book getById(Long id);

    List<Book> getAll();

    Book update(Long id, Book book);

    void delete(Long id);

    Book borrowBook(Long id, String username);
    Book returnBook(Long id, String username);

}
