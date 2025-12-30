package com.kbtu.bookservice.controller;

import com.kbtu.bookservice.dto.BookCreateRequest;
import com.kbtu.bookservice.dto.BookResponse;
import com.kbtu.bookservice.entity.Book;
import com.kbtu.bookservice.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public BookResponse create(@Valid @RequestBody BookCreateRequest request) {

        Book book = Book.create(
                request.getTitle(),
                request.getAuthor(),
                request.getPublishYear()
        );

        return new BookResponse(bookService.create(book));
    }

    @PostMapping("/{id}/borrow")
    public BookResponse borrow(@PathVariable Long id,
                               @RequestParam String username) {
        return new BookResponse(bookService.borrowBook(id, username));
    }

    @PostMapping("/{id}/return")
    public BookResponse returnBook(@PathVariable Long id,
                                   @RequestParam String username) {
        return new BookResponse(bookService.returnBook(id, username));
    }
}
