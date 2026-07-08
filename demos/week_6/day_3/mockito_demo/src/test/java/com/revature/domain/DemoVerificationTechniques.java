package com.revature.domain;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

// verify() verifies methods were called
// verification modes: times(), never(), atLeast(), atMost()
// ArgumentCaptor: capture and inspect what was passed
// InOrder: verify call sequence
// verifyNoMoreInteractions: strict verification

@ExtendWith(MockitoExtension.class)
@DisplayName("Demo Verification Techniques")
public class DemoVerificationTechniques {

    @Mock
    private UserRepository repository;

    @Mock
    private EmailClient emailClient;

    @InjectMocks
    private UserService userService;

    @Nested
    @DisplayName("Basic Verification")
    class BasicVerification {

        @Test
        @DisplayName("Verify method was called")
        void basicVerify() {
            when(repository.findById(1L)).thenReturn(Optional.of(new User(1L, "John", "john@test.com")));
            userService.getUser(1L);
            verify(repository).findById(1L);
        }

        @Test
        @DisplayName("Verify method has been called with specific argument")
        void verifyWithArgument() {
            when(repository.existsByEmail(anyString())).thenReturn(false);
            when(repository.save(any())).thenReturn(new User(1L, "John", "john@test.com"));
            when(emailClient.send(anyString(), anyString(), anyString())).thenReturn(true);

            userService.createUser("John", "john@test.com");

            verify(repository).existsByEmail("john@test.com");
            verify(emailClient).send(eq("john@test.com"), anyString(), anyString());
        }
    }
}
