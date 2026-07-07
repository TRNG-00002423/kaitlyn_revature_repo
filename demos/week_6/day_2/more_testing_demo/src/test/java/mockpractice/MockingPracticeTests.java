package mockpractice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.domain.User;
import com.revature.domain.UserRepository;
import com.revature.domain.UserService;

@ExtendWith(MockitoExtension.class)
public class MockingPracticeTests {
    @Mock
    private UserRepository repository;

    @Test
    void getUser_existingUser_returnsUser() {
        Optional<User> mockUser = Optional.of(new User("john@example.com"));
        when(repository.findById(1L)).thenReturn(mockUser);
        UserService service = new UserService(repository);
        User result = service.getUser(1L);
        assertEquals("john@example.com", result.getEmail());
    }

    @Test
    void defaultMockBehavior() {
        assertEquals(0, repository.count());
        assertNull(repository.save(new User())); // methods that return objects return null
        assertTrue(repository.findAll().isEmpty()); // methods that return collections return null
        assertFalse(repository.existsByEmail("john@test.com")); // boolean methods all return false
        repository.deleteById(1L); // does nothing
    }
}
