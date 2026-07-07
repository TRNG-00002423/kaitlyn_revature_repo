import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.week6.MockDatabase;
import com.example.week6.User;
import com.example.week6.UserRepository;

class UserRepositoryTest {

    private static MockDatabase database;
    private UserRepository repository;

    @BeforeAll
    static void setUpDatabase() {
        // This runs ONCE before all tests
        System.out.println("1. @BeforeAll: Setting up database");
        database = new MockDatabase();
        database.connect();
    }

    @AfterAll
    static void tearDownDatabase() {
        // This runs ONCE after all tests
        System.out.println("5. @AfterAll: Closing database");
        System.out.println("Disconnecting from database...");
        database.disconnect();
    }

    @BeforeEach
    void setUpTest() {

        System.out.println("  2. @BeforeEach: Preparing test");
        database.clearAll();
        repository = new UserRepository(database);

        // Optional: Insert baseline test data
        database.insert(new User(1, "Admin", "admin@test.com"));
    }

    @AfterEach
    void tearDownTest() {
        // Note: The database is cleared in setUpTest anyway
        System.out.println("  4. @AfterEach: Cleaning up test");
        System.out.println("Test completed, data will be reset");
    }

    @Test
    @DisplayName("Test 1: Add user and verify")
    void test1_addUser() {
        System.out.println("    3. @Test: Running test 1");
        // Add a user
        repository.save(new User(2, "John", "john@test.com"));

        // Verify it exists
        assertEquals(2, repository.count()); // Admin + John
    }

    @Test
    @DisplayName("Test 2: Should have fresh state")
    void test2_freshState() {
        System.out.println("    3. @Test: Running test 2");
        // This test should ONLY see the Admin user
        // NOT the John user from test1
        assertEquals(1, repository.count()); // Only Admin
    }

    @Test
    @DisplayName("Test 3: Database operations work independently")
    void test3_independentOperations() {
        System.out.println("    3. @Test: Running test 3");
        repository.save(new User(3, "Jane", "jane@test.com"));
        repository.save(new User(4, "Bob", "bob@test.com"));

        // Should have Admin + 2 new users
        assertEquals(3, repository.count());
    }

}