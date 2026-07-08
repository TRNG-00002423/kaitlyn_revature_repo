package com.revature.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

// stubbing patterns - controlling mock behavior
// when().thenReturn() - basic return values
// when().thenThrow() - simulate exceptions
// when().thenAnswer() - dynamic responses
// consecutive calls - different values each time
// doX.when() - for void methods and spies

@ExtendWith(MockitoExtension.class)
@DisplayName("Stubbing Patterns Demo")
public class DemoStubbingPatterns {
    @Mock
    private UserRepository repository;

    @Mock
    private EmailClient client;

    @Test
    @DisplayName("Return different values for different arguments using thenReturn()")
    void differentArgumentsDifferentResults() {
        when(repository.findById(1L)).thenReturn(Optional.of(new User(1L, "John", "john@test.com")));
        when(repository.findById(2L)).thenReturn(Optional.of(new User(2L, "Jane", "jane@test.com")));
        when(repository.findById(999L)).thenReturn(Optional.empty());

        assertEquals("John", repository.findById(1L).get().getName());
        assertEquals("Jane", repository.findById(2L).get().getName());
        assertTrue(repository.findById(999L).isEmpty());

    }

    @Test
    @DisplayName("Return value for any argument using matchers")
    void anyArgumentMatcher() {
        User defaultUser = new User(1L, "Default", "default@test.com");
        when(repository.findById(anyLong())).thenReturn(Optional.of(defaultUser));
        // all ids return the same user

        assertEquals("Default", repository.findById(1L).get().getName());
        assertEquals("Default", repository.findById(999L).get().getName());
        assertEquals("Default", repository.findById(12345L).get().getName());

    }

}
