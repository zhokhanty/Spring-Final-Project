package com.kbtu.bookservice.repository;

import com.kbtu.bookservice.entity.BookHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookHistoryRepository extends JpaRepository<BookHistory, Long> {

    Optional<BookHistory> findTopByBookIdAndToUserOrderByGivenAtDesc(Long bookId, String toUser);

}
