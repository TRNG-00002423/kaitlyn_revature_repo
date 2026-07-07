import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class) // Enables Mockito annotations
class UserServiceTest {

    @Mock
    private UserRepository repository; // Mock the dependency

    @Mock
    private EmailClient emailClient; // Mock the dependency

    @InjectMocks
    private UserService userService; // Inject mocks automatically

    @Test
    void getUser_existingUser_returnsUser() {
        User expectedUser = new User("John", "john@test.com");
        expectedUser.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(expectedUser));
        User actualUser = userService.getUser(1L);
    }
}
