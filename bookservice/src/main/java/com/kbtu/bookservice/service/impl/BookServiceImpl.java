package com.kbtu.bookservice.service.impl;

import com.kbtu.bookservice.entity.Book;
import com.kbtu.bookservice.entity.BookHistory;
import com.kbtu.bookservice.repository.BookHistoryRepository;
import com.kbtu.bookservice.repository.BookRepository;
import com.kbtu.bookservice.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookHistoryRepository bookHistoryRepository;

    public BookServiceImpl(BookRepository bookRepository,
                           BookHistoryRepository bookHistoryRepository) {
        this.bookRepository = bookRepository;
        this.bookHistoryRepository = bookHistoryRepository;
    }

    // CREATE
    @Override
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    // READ
    @Override
    public Book getById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Book not found with id: " + id));
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    // UPDATE
    @Override
    public Book update(Long id, Book updated) {
        Book book = getById(id);

        book.updateInfo(
                updated.getTitle(),
                updated.getAuthor(),
                updated.getPublishYear()
        );

        return book;
    }

    // DELETE
    @Override
    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new IllegalArgumentException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    // BORROW
    @Override
    public Book borrowBook(Long id, String username) {
        Book book = getById(id);

        if (book.getStatus() != Book.Status.AVAILABLE) {
            throw new IllegalStateException("Book is not available");
        }

        book.markBorrowed();

        BookHistory history = new BookHistory();
        history.setBook(book);
        history.setFromUser("LIBRARY");
        history.setToUser(username);
        history.setGivenAt(LocalDateTime.now());
        history.setStatus(Book.Status.BORROWED);

        bookHistoryRepository.save(history);

        return book;
    }

    // RETURN
    @Override
    public Book returnBook(Long id, String username) {
        Book book = getById(id);

        if (book.getStatus() != Book.Status.BORROWED) {
            throw new IllegalStateException("Book is not borrowed");
        }

        book.markReturned();

        BookHistory history = bookHistoryRepository
                .findTopByBookIdAndToUserOrderByGivenAtDesc(id, username)
                .orElseThrow(() ->
                        new IllegalStateException("Borrow history not found"));

        history.setReturnedAt(LocalDateTime.now());
        history.setStatus(Book.Status.AVAILABLE);

        bookHistoryRepository.save(history);

        return book;
    }
}
