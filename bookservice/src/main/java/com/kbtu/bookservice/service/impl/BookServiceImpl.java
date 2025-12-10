package com.kbtu.bookservice.service.impl;

import com.kbtu.bookservice.entity.Book;
import com.kbtu.bookservice.repository.BookRepository;
import com.kbtu.bookservice.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    // --- Конструктор для внедрения зависимости ---
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book update(Long id, Book updated) {
        Book book = getById(id);

        book.setTitle(updated.getTitle());
        book.setAuthor(updated.getAuthor());
        book.setYear(updated.getYear());
        book.setStatus(updated.getStatus());

        return bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book borrowBook(Long id, String username) {
        Book book = getById(id);
        if (book.getStatus() == Book.Status.BORROWED) {
            throw new RuntimeException("Book already borrowed");
        }
        book.setStatus(Book.Status.BORROWED);
        return bookRepository.save(book);
    }

    @Override
    public Book returnBook(Long id, String username) {
        Book book = getById(id);
        if (book.getStatus() == Book.Status.AVAILABLE) {
            throw new RuntimeException("Book is not borrowed");
        }
        book.setStatus(Book.Status.AVAILABLE);
        return bookRepository.save(book);
    }
}
