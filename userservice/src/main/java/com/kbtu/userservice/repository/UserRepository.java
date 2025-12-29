package com.kbtu.userservice.repository;

import com.kbtu.userservice.model.User;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Lock(LockModeType.OPTIMISTIC)
    Optional<User> findById(UUID id);
}

