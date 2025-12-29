package com.kbtu.userservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class User {

    @Id
    private UUID id;

    private String email;
    private String fullName;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Version
    private Long version;

    public User(@NotBlank String email, @NotBlank String fullName) {
        this.id = UUID.randomUUID();
        this.email = email;
        this.fullName = fullName;
        this.status = UserStatus.INACTIVE;
        this.version = 0L;
    }

    public void activate() {
        this.status = UserStatus.ACTIVE;
    }

    public void deactivate() {
        this.status = UserStatus.INACTIVE;
    }
}