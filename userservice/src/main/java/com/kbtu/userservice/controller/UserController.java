package com.kbtu.userservice.controller;

import com.kbtu.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UUID> createUser(
            @RequestBody @Valid CreateUserRequestDto dto
    ) {
        UUID id = userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PostMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable UUID id) {
        userService.deactivateUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/register")
    public ResponseEntity<UUID> register(@RequestBody @Valid CreateUserRequestDto dto) {
        UUID id = userService.registerUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }
}
