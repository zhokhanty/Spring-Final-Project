package com.kbtu.bookservice.controller;

import com.kbtu.bookservice.dto.BookCreateRequest;
import com.kbtu.bookservice.dto.BookResponse;
import com.kbtu.bookservice.entity.Book;
import com.kbtu.bookservice.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@RestController
@RequestMapping("/api/books")
@Tag(name = "Books API", description = "CRUD operations for books")
public class BookController {

    private final BookService bookService;
    // Spring внедрит бины BookService и KafkaProducerService
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @Operation(summary = "Create a new book")
    public BookResponse createBook(@Valid @RequestBody BookCreateRequest request) {
        // Сохраняем книгу
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setYear(request.getYear());
        book.setStatus(Book.Status.AVAILABLE);

        Book saved = bookService.create(book);
        return new BookResponse(saved);
    }

    @GetMapping
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book) {
        return bookService.update(id, book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }

    @PostMapping("/{id}/borrow")
    public Book borrow(@PathVariable Long id, @RequestParam String username) {
        Book borrowedBook = bookService.borrowBook(id, username);
        return bookService.borrowBook(id, username);
    }

    @PostMapping("/{id}/return")
    public Book returnBook(@PathVariable Long id, @RequestParam String username) {
        return bookService.returnBook(id, username);
    }
}
