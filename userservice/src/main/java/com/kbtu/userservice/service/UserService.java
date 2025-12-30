package com.kbtu.userservice.service;

import com.kbtu.userservice.controller.CreateUserRequestDto;
import com.kbtu.userservice.exception.UserNotFoundException;
//import com.kbtu.userservice.kafka.UserCreatedEvent;
//import com.kbtu.userservice.kafka.UserEventProperties;
import com.kbtu.userservice.model.User;
import com.kbtu.userservice.model.UserStatus;
import com.kbtu.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    // private final UserEventProperties eventProperties;

    @Transactional
    public UUID createUser(CreateUserRequestDto dto) {

        User user = new User(
                dto.getEmail(),
                dto.getFullName()
        );

        userRepository.save(user);

//        UserCreatedEvent event = new UserCreatedEvent(
//                UUID.randomUUID(),
//                user.getId(),
//                Instant.now()
//        );

//        kafkaTemplate.send(
//                eventProperties.getUserEventsTopic(),
//                user.getId().toString(),
//                event
//        );

        return user.getId();
    }

    @Transactional
    public void deactivateUser(UUID userId) {
        User user = findUser(userId);
        user.deactivate();
        userRepository.save(user);
    }

    private User findUser(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

}
