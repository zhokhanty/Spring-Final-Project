package com.kbtu.api.contracts.event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookBorrowedEvent {
    private Long bookId;
    private String username;
}
