package com.kbtu.bookservice.controller;

import com.kbtu.bookservice.entity.Book;
import com.kbtu.bookservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @PostMapping
    public Book create(@RequestBody Book book){
        return bookService.create(book);
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
        return bookService.borrowBook(id, username);
    }

    @PostMapping("/{id}/return")
    public Book returnBook(@PathVariable Long id, @RequestParam String username) {
        return bookService.returnBook(id, username);
    }

}
