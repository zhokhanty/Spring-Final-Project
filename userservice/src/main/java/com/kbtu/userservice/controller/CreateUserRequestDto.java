package com.kbtu.userservice.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequestDto {
    @NotBlank
    private String email;

    @NotBlank
    private String fullName;
}
