import org.junit.jupiter.api.Test;

import com.example.week6.UserValidation;
import com.example.week6.ValidationException;

import static org.junit.jupiter.api.Assertions.*;

public class UserValidationTest {
    UserValidation userValidation = new UserValidation();

    @Test
    void validateEmail_null_throwsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userValidation.validateEmail(null);
        }, "null email should throw IllegalArgumentException");
        assertEquals("Email cannot be null", exception.getMessage());
    }

    @Test
    void validateEmail_empty_throwsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userValidation.validateEmail("");
        }, "empty email should throw IllegalArgumentException");
        assertEquals("Email cannot be empty", exception.getMessage());
    }

    @Test
    void validateEmail_noAtSign_throwsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userValidation.validateEmail("noatsign");
        }, "email with no @ sign should throw IllegalArgumentException");
        assertEquals("Email must contain @", exception.getMessage());
    }

    @Test
    void validateEmail_borderAtSign_throwsIllegalArgumentException() {
        IllegalArgumentException namelessException = assertThrows(IllegalArgumentException.class, () -> {
            userValidation.validateEmail("@noname");
        }, "email with no name should throw IllegalArgumentException");
        IllegalArgumentException domainlessException = assertThrows(IllegalArgumentException.class, () -> {
            userValidation.validateEmail("nodomain@");
        }, "email with no domain should throw IllegalArgumentException");
        assertEquals("Email has invalid format", namelessException.getMessage());
        assertEquals("Email has invalid format", domainlessException.getMessage());
    }

    @Test
    void validatePassword_null_throwsValidationException() {
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            userValidation.validatePassword(null);
        }, "null password should throw ValidationException");
        assertEquals("Password cannot be null", exception.getMessage());
    }

    @Test
    void validatePassword_tooShort_throwsValidationException() {
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            userValidation.validatePassword("short");
        }, "password under 8 characters should throw ValidationException");
        assertEquals("Password must be at least 8 characters", exception.getMessage());
    }

    @Test
    void validatePassword_noUppercase_throwsValidationException() {
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            userValidation.validatePassword("nouppercase");
        }, "password with no uppercase letters should throw ValidationException");
        assertEquals("Password must contain an uppercase letter", exception.getMessage());
    }

    @Test
    void validatePassword_noLowercase_throwsValidationException() {
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            userValidation.validatePassword("NOLOWERCASE");
        }, "password with no lowercase letters should throw ValidationException");
        assertEquals("Password must contain a lowercase letter", exception.getMessage());
    }

    @Test
    void validateAge_negative_throwsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userValidation.validateAge(-1);
        }, "negative age should throw IllegalArgumentException");
        assertEquals("Age cannot be negative", exception.getMessage());
    }

    @Test
    void validateAge_tooHigh_throwsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userValidation.validateAge(151);
        }, "age > 150 should throw IllegalArgumentException");
        assertEquals("Age cannot exceed 150", exception.getMessage());
    }

    @Test
    void validateAge_validAges_dontThrowException() {
        assertDoesNotThrow(() -> {
            userValidation.validateAge(0);
            userValidation.validateAge(75);
            userValidation.validateAge(150);
        }, "ages >= 0 and <= 150 should not throw any exceptions");
    }

    @Test
    void validateEmail_multipleInvalidInputs_allThrowExceptions() {
        assertAll("Email validation exceptions",
                () -> assertThrows(IllegalArgumentException.class,
                        () -> userValidation.validateEmail(null)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> userValidation.validateEmail("")),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> userValidation.validateEmail("invalid")));
    }

}
