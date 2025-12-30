package com.kbtu.userservice.service;

import com.kbtu.userservice.controller.CreateUserRequestDto;
import com.kbtu.userservice.exception.UserNotFoundException;
import com.kbtu.userservice.kafka.UserCreatedEvent;
import com.kbtu.userservice.kafka.UserEventProperties;
import com.kbtu.userservice.model.User;
import com.kbtu.userservice.model.UserStatus;
import com.kbtu.userservice.repository.UserRepository;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Collections;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final UserEventProperties eventProperties;
    private final Keycloak keycloak;

    @Value("${keycloak.realm:kbtu-realm}")
    private String realm = "kbtu-realm";

    @Transactional
    public UUID createUser(CreateUserRequestDto dto) {

        User user = new User(
                dto.getEmail(),
                dto.getFullName()
        );

        userRepository.save(user);

        UserCreatedEvent event = new UserCreatedEvent(
                UUID.randomUUID(),
                user.getId(),
                Instant.now()
        );

        kafkaTemplate.send(
                eventProperties.getUserEventsTopic(),
                user.getId().toString(),
                event
        );

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

    @Transactional
    public UUID registerUser(CreateUserRequestDto dto) {
        UserRepresentation kcUser = new UserRepresentation();
        kcUser.setUsername(dto.getEmail());
        kcUser.setEmail(dto.getEmail());
        kcUser.setFirstName(dto.getFullName());
        kcUser.setEnabled(true);

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue("temporary-password123");
        credential.setTemporary(false);
        kcUser.setCredentials(Collections.singletonList(credential));

        Response response = keycloak.realm(realm).users().create(kcUser);

        if (response.getStatus() != 201) {
            throw new RuntimeException("Failed to create user in Keycloak. Status: " + response.getStatus());
        }

        User user = new User(dto.getEmail(), dto.getFullName());
        userRepository.save(user);

        return user.getId();
    }

}
