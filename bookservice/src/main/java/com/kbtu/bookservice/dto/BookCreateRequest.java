package com.kbtu.bookservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookCreateRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @Min(0)
    private Integer publishYear;
}
