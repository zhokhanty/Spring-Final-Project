package com.kbtu.bookservice.controller;

import com.kbtu.bookservice.entity.Book;
import com.kbtu.bookservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/books")
@Tag(name = "Books API", description = "CRUD operations for books")
public class BookController {

    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @PostMapping
    @Operation(summary = "Create a new book")
    public Book create(@RequestBody Book book){
        return bookService.create(book);
    }

    @GetMapping
    @Operation(summary = "Get all books")
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a book by id")
    public Book getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a book by id")
    public Book update(@PathVariable Long id, @RequestBody Book book) {
        return bookService.update(id, book);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book by id")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }

    @PostMapping("/{id}/borrow")
    public Book borrow(@PathVariable Long id, @RequestParam String username) {
        return bookService.borrowBook(id, username);
    }

    @PostMapping("/{id}/return")
    public Book returnBook(@PathVariable Long id, @RequestParam String username) {
        return bookService.returnBook(id, username);
    }


}
